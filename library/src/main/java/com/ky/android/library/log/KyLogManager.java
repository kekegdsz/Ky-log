package com.ky.android.library.log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author keke
 * 打印日志管理类
 */
public class KyLogManager {

    private KyLogConfig config;

    private static KyLogManager instance;

    private List<KyLogPrinter> printers = new ArrayList<>();

    private KyLogManager(KyLogConfig config, KyLogPrinter[] printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static KyLogManager getInstance() {
        return instance;
    }

    public static void init(@NonNull KyLogConfig config, KyLogPrinter... printers) {
        instance = new KyLogManager(config, printers);
    }

    public KyLogConfig getConfig() {
        return config;
    }

    public List<KyLogPrinter> getPrinters() {
        return printers;
    }

    public void addPrinter(KyLogPrinter printer){
        printers.add(printer);
    }

    public void removePrinter(KyLogPrinter printer){
        printers.remove(printer);
    }
}
