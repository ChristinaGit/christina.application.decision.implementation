package moe.christina.decision.model.data

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Layer(
    @PrimaryKey
    var id: Long,
    var name: String? = null,
    var consistency: Double? = null,

    var objects: RealmList<WeighableObject> = RealmList(),
    var weights: RealmList<Weight> = RealmList(),
    var comparisons: RealmList<Comparison> = RealmList()
) : RealmObject() {
    companion object {
        const val ID = "id"
        const val NAME = "name"
        const val CONSISTENCY = "consistency"
    }
}