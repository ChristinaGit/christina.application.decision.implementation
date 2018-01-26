package christina.application.decision.presentation.objects_viewer

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
    }

    override fun onUnsubscribe() {
        super.onUnsubscribe()
    }
}