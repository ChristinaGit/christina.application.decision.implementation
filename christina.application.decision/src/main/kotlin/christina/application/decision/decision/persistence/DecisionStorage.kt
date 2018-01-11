package christina.application.decision.decision.persistence

import christina.application.decision.decision.persistence.core.decision.DecisionStore
import christina.common.data.presistence.storage.core.Storage

interface DecisionStorage : Storage {
    val decisions: DecisionStore
}