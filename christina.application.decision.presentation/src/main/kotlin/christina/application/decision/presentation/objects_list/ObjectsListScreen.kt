package christina.application.decision.presentation.objects_list

import christina.application.decision.presentation.objects_list.domain.model.Object
import christina.common.event.core.NoticeEvent
import christina.library.android.architecture.mvp.screen_view.task.InitialTaskScreenView

interface ObjectsListScreen {
    val objectsListScreenView: InitialTaskScreenView<List<Object>, String>

    val onLoadObjectsList: NoticeEvent
}