package moe.christina.decision.model.data

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
data class NonValuableObject(
        @PrimaryKey
        @Required
        var id: Long?
) : RealmModel {
    constructor() : this(null)
}