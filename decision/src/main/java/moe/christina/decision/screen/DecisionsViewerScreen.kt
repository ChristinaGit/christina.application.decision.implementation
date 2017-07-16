package moe.christina.decision.screen

import io.reactivex.Observable
import moe.christina.common.core.Event
import moe.christina.mvp.screen.Screen

interface DecisionsViewerScreen : Screen {
    fun displayMessage(message: String)

    val onCreateDecision: Observable<Event>
}
