package christina.application.decision.persistence

import christina.application.decision.persistence.decision.store.DecisionStore
import christina.common.data.presistence.storage.fake.FakeStorage

class DecisionMemoryStorage
constructor(
    override val decisions: DecisionStore
) : FakeStorage(),
    DecisionStorage