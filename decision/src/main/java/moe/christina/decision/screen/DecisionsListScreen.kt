package moe.christina.decision.screen

import moe.christina.decision.model.Decision
import moe.christina.mvp.screen.Screen
import moe.christina.mvp.screen.behavior.ListScreenBehavior

interface DecisionsListScreen : Screen, ListScreenBehavior<Decision>