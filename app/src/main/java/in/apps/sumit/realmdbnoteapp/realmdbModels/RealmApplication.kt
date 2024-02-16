package `in`.apps.sumit.realmdbnoteapp.realmdbModels

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class RealmApplication {
    companion object{
        private val configuration = RealmConfiguration.create(schema = setOf(Note::class))
        val realm = Realm.open(configuration)
    }

}