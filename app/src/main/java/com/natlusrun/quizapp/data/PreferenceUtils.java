package com.natlusrun.quizapp.data;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {

    public static SharedPreferences mPreferences;
    private static final String APP_THEME ="current.app.theme";

    public static void init(Context context) {
        mPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);

    }

    public static void saveAppTheme(int position) {
        mPreferences.edit().putInt(APP_THEME, position).apply();
    }

    public static int getAppTheme() {
        return mPreferences.getInt(APP_THEME, 22);
    }
}
