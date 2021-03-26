package com.ky.android.library.log;

/**
 * @author keke
 * @date 2021/3/26 14:15
 * @describe TIPS: 打印堆栈信息
 * @email keke@ky-tech.com.cn
 **/
public class KyStackTraceFormatter implements KyLogFormatter<StackTraceElement[]> {
    @Override
    public String format(StackTraceElement[] stackTrace) {
        StringBuilder sb = new StringBuilder(128);
        if (stackTrace == null || stackTrace.length == 0) {
            return null;
        } else if (stackTrace.length == 1) {
            return "\t-" + stackTrace[0].toString();
        } else {
            for (int i = 0, len = stackTrace.length; i < len; i++) {
                if (i == 0) {
                    sb.append("stackTrace:  \n");
                }
                if (i != len - 1) {
                    sb.append("\t├ ");
                    sb.append(stackTrace[i].toString());
                    sb.append("\n");
                } else {
                    sb.append("\t└ ");
                    sb.append(stackTrace[i].toString());
                }
            }
            return sb.toString();
        }
    }
}
