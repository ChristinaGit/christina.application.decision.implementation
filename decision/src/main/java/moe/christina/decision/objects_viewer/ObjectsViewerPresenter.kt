package moe.christina.decision.objects_viewer

import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import moe.christina.android.templates.behavior.task.onRequestPerformTask
import moe.christina.common.core.event.Event
import moe.christina.decision.core.BaseDecisionPresenter
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

class ObjectsViewerPresenter
@Inject
constructor(
    screen: ObjectsViewerScreen
) : BaseDecisionPresenter<ObjectsViewerScreen>(screen),
    AnkoLogger {
    @CallSuper
    override fun onBindScreen() {
        super.onBindScreen()

        screenObservers.add(screen.onRequestPerformTask.subscribe(this::createObjectHandler))
    }

    override fun onUnbindScreen() {
        super.onUnbindScreen()

        screenObservers.dispose()
    }

    private fun createObjectHandler(event: Event) {
    }

    private val screenObservers: CompositeDisposable = CompositeDisposable()
}