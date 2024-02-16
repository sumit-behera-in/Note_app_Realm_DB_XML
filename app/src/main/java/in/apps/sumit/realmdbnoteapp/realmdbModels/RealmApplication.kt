package `in`.apps.sumit.realmdbnoteapp.realmdbModels

import android.app.Application
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class RealmApplication:Application() {
    companion object{
        lateinit var realm : Realm
    }

    override fun onCreate() {
        super.onCreate()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    Note::class
                )
            )
        )
    }
}