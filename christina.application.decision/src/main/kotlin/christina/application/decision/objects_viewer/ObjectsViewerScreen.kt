package christina.application.decision.objects_viewer

import christina.common.event.core.NoticeEvent

interface ObjectsViewerScreen {
    val onRequestCreateObject: NoticeEvent
}