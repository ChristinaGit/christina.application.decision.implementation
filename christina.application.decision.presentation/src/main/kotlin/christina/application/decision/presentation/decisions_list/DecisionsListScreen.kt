package christina.application.decision.presentation.decisions_list

import christina.common.event.core.NoticeEvent
import christina.library.android.architecture.mvp.screen_view.task.InitialTaskScreenView

interface DecisionsListScreen {
    val decisionsListScreenView: InitialTaskScreenView<List<String?>, String>

    val onLoadDecisionsList: NoticeEvent
}