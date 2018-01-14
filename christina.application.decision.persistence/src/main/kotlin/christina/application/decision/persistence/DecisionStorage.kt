package christina.application.decision.persistence

import christina.application.decision.persistence.decision.store.DecisionStore
import christina.common.data.presistence.storage.core.Storage

interface DecisionStorage : Storage {
    val decisions: DecisionStore
}