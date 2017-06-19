package moe.christina.decision.screen

import moe.christina.mvp.Screen

interface DecisionsViewerScreen : Screen {
    fun displayMessage(message: String)
}
