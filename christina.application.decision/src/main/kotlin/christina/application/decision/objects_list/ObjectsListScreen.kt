package christina.application.decision.objects_list

import christina.common.event.core.NoticeEvent
import christina.application.decision.objects_list.domain.model.Object
import christina.library.android.architecture.mvp.screen_view.task.TaskScreenView

interface ObjectsListScreen {
    val objectsListScreenView: TaskScreenView<List<Object>, String>

    val onLoadObjectsList : NoticeEvent
}