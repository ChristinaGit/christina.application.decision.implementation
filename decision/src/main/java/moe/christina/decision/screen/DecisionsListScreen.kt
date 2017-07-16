package moe.christina.decision.screen

import moe.christina.decision.model.data.Layer
import moe.christina.mvp.screen.Screen
import moe.christina.mvp.screen.behavior.RefreshableScreenBehavior

interface DecisionsListScreen :
    Screen,
    RefreshableScreenBehavior<List<Layer>>