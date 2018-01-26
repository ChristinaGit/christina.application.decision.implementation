package christina.application.decision.presentation.objects_list

import christina.application.decision.presentation.core.BaseDecisionPresenter
import christina.application.decision.presentation.core.scope.FragmentScope
import christina.application.decision.presentation.objects_list.domain.usecase.GetObjectsInteractor
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.AnkoLogger
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

//        screen.onLoadObjectsList += onLoadObjectsList
    }

    override fun onUnsubscribe() {
        super.onUnsubscribe()

//        screen.onLoadObjectsList -= onLoadObjectsList

        onLoadObjectsObserver = null
    }

//    private val onLoadObjectsList = eventListener {
//        onLoadObjectsObserver = getObjectsInteractor()
//            .observeOn(RxSchedulers.main())
//            .displayTask(screen.objectsListScreenView, { it.message ?: it.toString() })
//            .subscribe({}, { warn("Failed to getAll decisions", it) })
//    }

    private var onLoadObjectsObserver: Disposable? = null
        set(value) {
            field?.dispose()

            field = value
        }
}