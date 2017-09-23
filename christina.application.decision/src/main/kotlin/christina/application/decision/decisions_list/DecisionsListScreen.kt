package christina.application.decision.decisions_list

import christina.common.event.core.NoticeEvent
import christina.application.decision.decisions_list.domain.model.Decision
import christina.library.android.architecture.mvp.screen_view.task.TaskScreenView

interface DecisionsListScreen {
    val decisionsListScreenView: TaskScreenView<List<Decision>, String>

    val onLoadDecisionsList: NoticeEvent
}