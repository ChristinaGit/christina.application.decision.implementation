package christina.application.decision.presentation.objects_viewer

import christina.common.event.core.NoticeEvent

interface ObjectsViewerScreen {
    val onRequestCreateObject: NoticeEvent
}