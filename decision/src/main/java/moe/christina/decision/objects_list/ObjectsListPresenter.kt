package moe.christina.decision.objects_list

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moe.christina.android.templates.behavior.task.displayTaskOnScreen
import moe.christina.android.templates.behavior.task.onRequestPerformTask
import moe.christina.common.core.RxSchedulers
import moe.christina.common.core.event.Event
import moe.christina.decision.core.BaseDecisionPresenter
import moe.christina.decision.objects_list.domain.usecase.GetObjectsInteractor
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn
import javax.inject.Inject

class ObjectsListPresenter
@Inject
constructor(
    screen: ObjectsListScreen,
    private val getObjectsInteractor: GetObjectsInteractor
) : BaseDecisionPresenter<ObjectsListScreen>(screen), AnkoLogger {
    override fun onBindScreen() {
        super.onBindScreen()

        screenObservers.add(screen.onRequestPerformTask.subscribe(this::loadDecisionsHandler))
    }

    override fun onUnbindScreen() {
        super.onUnbindScreen()

        onLoadObjectsObserver = null
    }

    private fun loadDecisionsHandler(event: Event) {
        onLoadObjectsObserver = getObjectsInteractor()
            .observeOn(RxSchedulers.main())
            .displayTaskOnScreen(screen, { Unit }, { it.message ?: it.toString() })
            .subscribe({}, { warn("Failed to load decisions", it) })
    }

    private val screenObservers: CompositeDisposable = CompositeDisposable()

    private var onLoadObjectsObserver: Disposable? = null
        set(value) {
            field?.dispose()

            field = value
        }
}