package moe.christina.decision.model.data

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
data class Decision(
        @PrimaryKey
        @Required
        var id: Long?,
        var name: String?,
        var objects: RealmList<NonValuableObject>?
) : RealmModel {
    constructor() : this(null, null, null)
}