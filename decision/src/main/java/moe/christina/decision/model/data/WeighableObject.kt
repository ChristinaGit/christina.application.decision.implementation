package moe.christina.decision.model.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class WeighableObject(
    @PrimaryKey
    var id: Long,
    var name: String? = null
) : RealmObject() {
    companion object {
        const val ID = "id"
        const val NAME = "name"
    }
}