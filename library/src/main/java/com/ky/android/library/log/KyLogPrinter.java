package com.ky.android.library.log;

import androidx.annotation.NonNull;

/**
 * @author keke
 * @date 2021/3/26 11:51
 * @describe TIPS:
 * @email keke@ky-tech.com.cn
 **/
public interface KyLogPrinter {

    /**
     *
     * @param config
     * @param level
     * @param tag
     * @param contentString
     */
    void print(@NonNull KyLogConfig config, int level, String tag, @NonNull String contentString);
}
