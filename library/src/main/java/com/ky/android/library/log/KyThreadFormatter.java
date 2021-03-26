package com.ky.android.library.log;

/**
 * @author keke
 * @date 2021/3/26 14:14
 * @describe TIPS:
 * @email keke@ky-tech.com.cn
 **/
public class KyThreadFormatter implements KyLogFormatter<Thread> {
    @Override
    public String format(Thread data) {
        return "Thread:" + data.getName();
    }
}
