package moe.christina.decision.screen

import moe.christina.mvp.screen.Screen

interface DecisionsViewerScreen : Screen {
    fun displayMessage(message: String)
}
