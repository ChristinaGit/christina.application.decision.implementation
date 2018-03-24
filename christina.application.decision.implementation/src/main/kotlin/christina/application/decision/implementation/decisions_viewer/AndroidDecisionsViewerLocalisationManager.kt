package christina.application.decision.implementation.decisions_viewer

import android.content.Context
import christina.application.decision.implementation.R
import christina.application.decision.presentation.decisions_viewer.DecisionsViewerLocalisationManager

class AndroidDecisionsViewerLocalisationManager(
    private var context: Context
) : DecisionsViewerLocalisationManager {
    override fun decisionCreated(decisionName: String?): String =
        if (decisionName === null) {
            context.getString(R.string.decisions_viewer_message_created_unnamed)
        } else {
            context.getString(R.string.decisions_viewer_message_created, decisionName)
        }
}