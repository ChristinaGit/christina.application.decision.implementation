package christina.application.decision.presentation.objects_viewer

import christina.common.event.core.eventListener
import christina.common.event.core.minusAssign
import christina.common.event.core.plusAssign
import christina.application.decision.presentation.core.BaseDecisionPresenter
import christina.application.decision.presentation.core.scope.ActivityScope
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

@ActivityScope
class ObjectsViewerPresenter
@Inject
constructor(
    screen: ObjectsViewerScreen
) : BaseDecisionPresenter<ObjectsViewerScreen>(screen),
    AnkoLogger {
    override fun onSubscribe() {
        super.onSubscribe()

        screen.onRequestCreateObject += onRequestCreateObject
    }

    override fun onUnsubscribe() {
        super.onUnsubscribe()

        screen.onRequestCreateObject -= onRequestCreateObject
    }

    private val onRequestCreateObject = eventListener {

    }
}