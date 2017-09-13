package moe.christina.decision.decisions_viewer

import moe.christina.android.templates.behavior.task.HasTaskScreenBehavior
import moe.christina.android.templates.behavior.task.TaskScreenBehaviorHandler
import moe.christina.common.core.event.Event
import moe.christina.decision.decisions_viewer.domain.model.CreatedDecision

typealias HasCreateDecisionBehavior = HasTaskScreenBehavior<Event, CreatedDecision, Unit, String>
typealias CreateDecisionBehaviorHandler = TaskScreenBehaviorHandler<Event, CreatedDecision, Unit, String>