package myapp.nigam.com.mymoviesapp.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppController extends Application {

    private static RealmConfiguration realmConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());

        realmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static Realm getRealmInstance() {

        return Realm.getInstance(realmConfiguration);
    }
}
