package com.yuan.music_x.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.yuan.music_x.Constants;
import com.yuan.music_x.bean.SongInfo;

/**
 * yuan
 * 2020/2/27
 **/
public class SharePreferenceUtil {
    
    private String TAG = this.getClass().getSimpleName();

    private static SharePreferenceUtil instance;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private SharePreferenceUtil() {
        super();
    }

    public static SharePreferenceUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (SharePreferenceUtil.class) {
                if (instance == null) {
                    if (sharedPreferences == null) sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    instance = new SharePreferenceUtil();
                }
            }
        }
        return instance;
    }

    public SongInfo getLatestSong() {
        return GsonUtil.fromJson(getString(Constants.SPKey.LATEST_SONG, ""), SongInfo.class);
    }

    public void saveLatestSong(SongInfo songInfo) {
        saveString(Constants.SPKey.LATEST_SONG, GsonUtil.toJson(songInfo));
    }

    public int getMode() {
        return sharedPreferences.getInt(Constants.SPKey.Mode, 1);
    }

    public void saveMode(int mode) {
        editor.putInt(Constants.SPKey.Mode, mode).apply();
    }

    private String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    private void saveString(String key, String value) {
        editor.putString(key, value).apply();
    }
}
