package moe.christina.decision.objects_viewer

import moe.christina.android.templates.behavior.task.HasTaskScreenBehavior
import moe.christina.android.templates.behavior.task.TaskScreenBehaviorHandler
import moe.christina.common.core.event.Event
import moe.christina.decision.objects_viewer.domain.model.CreatedObject

typealias HasCreateObjectBehavior = HasTaskScreenBehavior<Event, CreatedObject, Unit, String>
typealias CreateObjectBehaviorHandler = TaskScreenBehaviorHandler<Event, CreatedObject, Unit, String>