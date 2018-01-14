package christina.application.decision.presentation.decisions_viewer

import christina.common.event.core.NoticeEvent
import christina.library.android.architecture.mvp.screen_view.content.ContentScreenView

interface DecisionsViewerScreen {
    val createdDecisionScreenView: ContentScreenView<String?>

    val onRequestCreateDecision: NoticeEvent
}