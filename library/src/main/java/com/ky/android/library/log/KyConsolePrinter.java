package com.ky.android.library.log;

import android.util.Log;

import androidx.annotation.NonNull;

import static com.ky.android.library.log.KyLogConfig.MAX_LEN;

/**
 * @author keke
 * @date 2021/3/26 14:29
 * @describe TIPS:
 * @email keke@ky-tech.com.cn
 **/
public class KyConsolePrinter implements KyLogPrinter {
    @Override
    public void print(@NonNull KyLogConfig config, int level, String tag, @NonNull String contentString) {
        int len = contentString.length();
        int countOfSub = len / MAX_LEN;
        if (countOfSub > 0) {
            StringBuilder log = new StringBuilder();
            int index = 0;
            for (int i = 0; i < countOfSub; i++) {
                log.append(contentString.substring(index, index + MAX_LEN));
                index += MAX_LEN;
            }
            if (index != len) {
                log.append(contentString.substring(index, len));
            }
            Log.println(level, tag, log.toString());
        } else {
            Log.println(level, tag, contentString);
        }
    }
}
