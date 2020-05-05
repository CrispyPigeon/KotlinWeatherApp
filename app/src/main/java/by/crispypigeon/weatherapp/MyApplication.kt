package by.crispypigeon.weatherapp

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val realmConf = RealmConfiguration.Builder()
            .name("weatherapp.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()

        Realm.setDefaultConfiguration(realmConf)
    }
}