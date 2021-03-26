package com.ky.android.library.log;

/**
 * @author keke
 * 日志框架配置
 */
public abstract class KyLogConfig {

    static int MAX_LEN = 512;
    static KyStackTraceFormatter KY_STACK_TRACE_FORMATTER = new KyStackTraceFormatter();
    static KyThreadFormatter KY_THREAD_FORMATTER = new KyThreadFormatter();

    public JsonParser injectJsonParser() {
        return null;
    }

    public String getGlobalTag() {
        return "KyLog";
    }

    public boolean enable() {
        return true;
    }

    public boolean includeThread() {
        return false;
    }

    public int stactTraceDepth() {
        return 5;
    }

    public KyLogPrinter[] printers() {
        return null;
    }

    public interface JsonParser {
        String toJson(Object src);
    }
}
