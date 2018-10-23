package com.example.qwert.tools;

import com.hwangjr.rxbus.Bus;

public final class Rxbus {
    public static Bus mBus;

    public synchronized static Bus get() {
        if (mBus == null) {
            mBus = new Bus();
        }
        return mBus;
    }
}
