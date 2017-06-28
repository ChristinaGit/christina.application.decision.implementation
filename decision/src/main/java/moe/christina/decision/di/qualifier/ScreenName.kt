package moe.christina.decision.di.qualifier

import moe.christina.mvp.di.qualifier.Qualifier

object ScreenName {
    private const val NAME_PREFIX = "screen" + Qualifier.NAME_SEPARATOR
    const val DECISIONS_VIEWER = NAME_PREFIX + "DecisionsViewer"
    const val DECISIONS_LIST = NAME_PREFIX + "DecisionsList"
}