package christina.application.decision.decision.persistence.storage

import christina.application.decision.decision.persistence.storage.store.DecisionStore
import christina.common.data.presistence.storage.core.Storage

interface DecisionStorage : Storage {
    val decisions: DecisionStore
}