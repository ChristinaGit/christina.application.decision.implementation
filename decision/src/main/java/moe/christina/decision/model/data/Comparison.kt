package moe.christina.decision.model.data

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Comparison(
    var lhs: WeighableObject,
    var rhs: WeighableObject,
    var lhsValue: Int,
    var rhsValue: Int
) : RealmObject()