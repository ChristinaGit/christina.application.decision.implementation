package christina.application.decision.presentation.objects_list

import christina.application.decision.presentation.objects_list.domain.model.Object
import christina.common.rx.event.UnitEvent
import christina.library.android.architecture.mvp.screen_view.task.IndeterminateTaskScreenView
import io.reactivex.Observable

interface ObjectsListScreen {
    val objectsListScreenView: IndeterminateTaskScreenView<List<Object>, String>

    val onLoadObjectsList: Observable<UnitEvent>
}