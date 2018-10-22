package com.example.qwert.tools;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class T {


    private static Application a;

    public T() {

    }

    public static void init(Application a) {
        T.a = a;
    }

    public static void s(String msg) {
        if (a == null) return;
        T.s(a, msg);
    }

    public static void l(String msg) {
        if (a == null) return;
        T.l(a, msg);

    }

    public static void s(Context context, String t) {
        Toast.makeText(context, t, Toast.LENGTH_SHORT).show();
    }

    public static void l(Context context, String t) {
        Toast.makeText(context, t, Toast.LENGTH_LONG).show();
    }


}
