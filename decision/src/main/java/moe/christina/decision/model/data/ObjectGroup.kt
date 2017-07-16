package moe.christina.decision.model.data

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class ObjectGroup(
    @PrimaryKey
    var id: Long,
    var name: String? = null,
    var color: Int? = null,

    var objects: RealmList<WeighableObject> = RealmList()
) : RealmObject()