package christina.application.decision.decision.persistence.memory

import christina.application.decision.decision.persistence.DecisionStorage
import christina.application.decision.decision.persistence.core.decision.DecisionStore
import christina.common.data.presistence.storage.fake.FakeStorage

class DecisionMemoryStorage
constructor(
    override val decisions: DecisionStore
) : FakeStorage(),
    DecisionStorage