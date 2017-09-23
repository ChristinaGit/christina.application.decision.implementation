package christina.application.decision.objects_list

import io.reactivex.disposables.Disposable
import christina.library.android.common.rx.RxSchedulers
import christina.common.event.core.eventListener
import christina.common.event.core.minusAssign
import christina.common.event.core.plusAssign
import christina.application.decision.core.BaseDecisionPresenter
import christina.application.decision.objects_list.domain.usecase.GetObjectsInteractor
import christina.library.android.architecture.mvp.di.scope.FragmentScope
import christina.library.android.architecture.mvp.interactor.invoke
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn
import javax.inject.Inject

@FragmentScope
class ObjectsListPresenter
@Inject
constructor(
    screen: ObjectsListScreen,
    private val getObjectsInteractor: GetObjectsInteractor
) : BaseDecisionPresenter<ObjectsListScreen>(screen),
    AnkoLogger {

    override fun onSubscribe() {
        super.onSubscribe()

        screen.onLoadObjectsList += onLoadObjectsList
    }

    override fun onUnsubscribe() {
        super.onUnsubscribe()

        screen.onLoadObjectsList -= onLoadObjectsList

        onLoadObjectsObserver = null
    }

    private val onLoadObjectsList = eventListener {
        onLoadObjectsObserver = getObjectsInteractor()
            .observeOn(RxSchedulers.main())
            .displayTask(screen.objectsListScreenView, { it.message ?: it.toString() })
            .subscribe({}, { warn("Failed to getAll decisions", it) })
    }

    private var onLoadObjectsObserver: Disposable? = null
        set(value) {
            field?.dispose()

            field = value
        }
}