package moe.christina.decision.objects_list

import moe.christina.android.templates.behavior.task.HasTaskScreenBehavior
import moe.christina.android.templates.behavior.task.TaskScreenBehaviorHandler
import moe.christina.common.core.event.Event
import moe.christina.decision.objects_viewer.domain.model.CreatedObject

typealias HasLoadObjectsBehavior = HasTaskScreenBehavior<Event, List<CreatedObject>, Unit, String>
typealias LoadObjectsBehaviorHandler = TaskScreenBehaviorHandler<Event, List<CreatedObject>, Unit, String>