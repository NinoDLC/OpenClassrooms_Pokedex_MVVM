package fr.delcey.pokedex;

import android.app.Application;

public class MainApplication extends Application {

    private static Application sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
    }

    public static Application getInstance() {
        return sInstance;
    }
}
