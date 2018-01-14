package christina.application.decision.presentation.objects_viewer

import android.os.Bundle
import android.view.View
import christina.application.decision.presentation.R
import christina.application.decision.presentation.core.BaseDecisionActivity
import christina.application.decision.presentation.objects_list.ObjectsListFragment
import christina.common.event.Events
import christina.common.event.core.NoticeEvent
import christina.common.event.core.NoticeInternalEvent
import christina.common.event.core.invoke
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find

class ObjectsViewerActivity :
    BaseDecisionActivity(),
    ObjectsViewerScreen,
    AnkoLogger {

    override val onRequestCreateObject: NoticeEvent
        get() = onRequestCreateObjectEvent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.templates_viewer)
        setSupportActionBar(find(R.id.templates_toolbar))

        find<View>(R.id.templates_viewer_fab).setOnClickListener(::onViewClick)

        if (supportFragmentManager.findFragmentById(R.id.templates_viewer_fragment_container) == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.templates_viewer_fragment_container, ObjectsListFragment.newInstance())
                .commit()
        }
    }

    private fun onViewClick(view: View) {
        when (view.id) {
            R.id.templates_viewer_fab -> onFabClick()
        }
    }

    private fun onFabClick() {
        onRequestCreateObjectEvent()
    }

    private val onRequestCreateObjectEvent: NoticeInternalEvent = Events.basic()
}