package com.ky.android.library.log;

/**
 * @author keke
 * @date 2021/3/26 14:12
 * @describe TIPS:  日志打印格式化接口
 * @email keke@ky-tech.com.cn
 **/
public interface KyLogFormatter<T> {

    String format(T data);
}
