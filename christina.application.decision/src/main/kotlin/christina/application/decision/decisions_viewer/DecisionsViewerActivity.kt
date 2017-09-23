package christina.application.decision.decisions_viewer

import android.os.Bundle
import android.view.View
import christina.application.decision.R
import christina.application.decision.core.BaseDecisionActivity
import christina.application.decision.decisions_list.DecisionsListFragment
import christina.application.decision.decisions_viewer.domain.model.CreatedDecision
import christina.common.event.Events
import christina.common.event.core.NoticeEvent
import christina.common.event.core.NoticeInternalEvent
import christina.common.event.core.invoke
import christina.library.android.architecture.mvp.screen_view.content.ContentScreenView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class DecisionsViewerActivity :
    BaseDecisionActivity(),
    DecisionsViewerScreen,
    AnkoLogger {
    override val onRequestCreateDecision: NoticeEvent
        get() = onRequestCreateDecisionEvent

    override val createdDecisionScreenView: ContentScreenView<CreatedDecision> =
        object : ContentScreenView<CreatedDecision> {
            override var visible: Boolean = false

            override fun display(content: CreatedDecision) {
                toast("Decision created : ${content.name}")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.templates_viewer)
        setSupportActionBar(find(R.id.templates_toolbar))

        find<View>(R.id.templates_viewer_fab).setOnClickListener(this::onViewClick)

        if (supportFragmentManager.findFragmentById(R.id.templates_viewer_fragment_container) == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.templates_viewer_fragment_container, DecisionsListFragment.newInstance())
                .commit()
        }
    }

    private fun onViewClick(view: View) {
        when (view.id) {
            R.id.templates_viewer_fab -> onFabClick()
        }
    }

    private fun onFabClick() {
        onRequestCreateDecisionEvent()
    }

    private val onRequestCreateDecisionEvent: NoticeInternalEvent = Events.basic()
}