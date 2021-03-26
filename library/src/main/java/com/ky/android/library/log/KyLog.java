package com.ky.android.library.log;


import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * tips:
 * 1、打印堆栈信息
 * 2、file输出
 * 3、模拟控制在
 */
public class KyLog {

    private static final String KY_LOG_PACKAGE;

    static {
        String className = KyLog.class.getName();
        KY_LOG_PACKAGE = className.substring(0, className.lastIndexOf('.') + 1);
    }

    public static void v(Object... contents) {
        log(KyLogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(KyLogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(KyLogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(KyLogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(KyLogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(KyLogType.I, tag, contents);
    }

    public static void w(Object... contents) {
        log(KyLogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(KyLogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(KyLogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(KyLogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(KyLogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(KyLogType.A, tag, contents);
    }

    public static void log(@KyLogType.TYPE int type, Object... contents) {
        log(KyLogManager.getInstance().getConfig(), type, KyLogManager.getInstance().getConfig().getGlobalTag(), contents);
    }

    public static void log(@KyLogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(KyLogManager.getInstance().getConfig(), type, tag, contents);
    }

    public static void log(@NonNull KyLogConfig config, @KyLogType.TYPE int type, @NonNull String tag, Object... contents) {
        if (!config.enable()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (config.includeThread()) {
            String threadInfo = KyLogConfig.KY_THREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }
        if (config.stactTraceDepth() > 0) {
            String stactTrace = KyLogConfig.KY_STACK_TRACE_FORMATTER.format(
                    KyStackTraceUtil.getCroppedRealStackTrack(new Throwable().getStackTrace(), KY_LOG_PACKAGE, config.stactTraceDepth()));
            sb.append(stactTrace).append("\n");
        }
        String body = parseBody(contents, config);
        //替换转义字符\
        if (body != null) {
            body = body.replace("\\\"", "\"");
        }
        sb.append(body);
        List<KyLogPrinter> printers = config.printers() != null
                ? Arrays.asList(config.printers())
                : KyLogManager.getInstance().getPrinters();
        if (printers == null) {
            return;
        }
        //打印日志
        for (KyLogPrinter printer : printers) {
            printer.print(config, type, tag, sb.toString());
        }
    }

    private static String parseBody(@NonNull Object[] contents, @NonNull KyLogConfig config) {
        if (config.injectJsonParser() != null) {
            //只有一个数据且为String的情况下不再进行序列化
            if (contents.length == 1 && contents[0] instanceof String) {
                return (String) contents[0];
            }
            return config.injectJsonParser().toJson(contents);
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : contents) {
            sb.append(o.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
