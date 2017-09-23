package christina.application.decision.decisions_viewer

import christina.common.event.core.NoticeEvent
import christina.application.decision.decisions_viewer.domain.model.CreatedDecision
import christina.library.android.architecture.mvp.screen_view.content.ContentScreenView

interface DecisionsViewerScreen {
    val createdDecisionScreenView: ContentScreenView<CreatedDecision>

    val onRequestCreateDecision: NoticeEvent
}