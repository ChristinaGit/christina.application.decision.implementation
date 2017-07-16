package moe.christina.decision.model.data

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Weight(
    var target: WeighableObject,
    var value: Int
) : RealmObject()