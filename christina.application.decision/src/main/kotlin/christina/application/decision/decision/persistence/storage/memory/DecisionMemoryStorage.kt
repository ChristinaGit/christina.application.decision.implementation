package christina.application.decision.decision.persistence.storage.memory

import christina.application.decision.decision.persistence.storage.DecisionStorage
import christina.application.decision.decision.persistence.storage.store.DecisionStore
import christina.application.decision.decision.persistence.storage.store.memory.DecisionMemoryStore
import christina.common.data.presistence.storage.fake.FakeStorage

class DecisionMemoryStorage : FakeStorage(), DecisionStorage {
    override val decisions: DecisionStore = DecisionMemoryStore()
}