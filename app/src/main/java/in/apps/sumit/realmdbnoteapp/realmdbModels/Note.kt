package `in`.apps.sumit.realmdbnoteapp.realmdbModels

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.util.Date

class Note :RealmObject {
    @PrimaryKey var _id : ObjectId = ObjectId()
    var content :String = ""
    var date : Date = Date()
    var title : String = ""
}