package christina.application.decision.decisions_list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import christina.common.event.Events
import christina.common.event.core.NoticeEvent
import christina.common.event.core.NoticeInternalEvent
import christina.common.event.core.invoke
import christina.common.state_coordinator.basic.BasicStateCoordinator
import christina.common.state_coordinator.core.StateCoordinator
import christina.common.state_coordinator.core.stateChanger
import christina.common.state_coordinator.core.stateChecker
import moe.christina.decision.R
import christina.application.decision.core.BaseDecisionFragment
import christina.application.decision.decisions_list.domain.model.Decision
import christina.library.android.architecture.mvp.screen_view.ScreenView
import christina.library.android.architecture.mvp.screen_view.content.ContentScreenView
import christina.library.android.architecture.mvp.screen_view.task.TaskScreenView
import org.jetbrains.anko.AnkoLogger

class DecisionsListFragment :
    BaseDecisionFragment(),
    DecisionsListScreen,
    AnkoLogger {
    override val onLoadDecisionsList: NoticeEvent
        get() = onLoadDecisionsListEvent

    override val decisionsListScreenView: TaskScreenView<List<Decision>, String> =
        object : TaskScreenView<List<Decision>, String> {
            override var visible: Boolean
                get() = visibilityCoordinator.getState(R.id.templates_list_content)
                set(value) = visibilityCoordinator.setState(R.id.templates_list_content, value)

            override fun display(content: List<Decision>) {
                activity.toast("Lost loaded. Size: ${content.size}")


            }

            override val progressScreenView: ScreenView =
                object : ScreenView {
                    override var visible: Boolean
                        get() = visibilityCoordinator.getState(R.id.templates_loading_progress)
                        set(value) = visibilityCoordinator.setState(R.id.templates_loading_progress, value)
                }

            override val errorScreenView: ContentScreenView<String> =
                object : ContentScreenView<String> {
                    override var visible: Boolean
                        get() = visibilityCoordinator.getState(R.id.templates_loading_error)
                        set(value) = visibilityCoordinator.setState(R.id.templates_loading_error, value)

                    override fun display(content: String) {
                        loadingErrorView.text = content
                    }
                }
        }

    private val visibilityCoordinator: StateCoordinator<View, Boolean>
        = BasicStateCoordinator(
        stateChanger { visible -> visibility = if (visible) View.VISIBLE else View.GONE },
        stateChecker { (visibility == View.VISIBLE && it) || (visibility != View.VISIBLE && !it) })

    companion object {
        @JvmStatic
        fun newInstance(): DecisionsListFragment = DecisionsListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.tempaltes_refreshable_list, container, false)

        view.run {
            listContentView = find(R.id.templates_list_content)
            loadingErrorView = find(R.id.templates_loading_error)

            visibilityCoordinator.add(R.id.templates_list_content, listContentView)
            visibilityCoordinator.add(R.id.templates_loading_progress, find(R.id.templates_loading_progress))
            visibilityCoordinator.add(R.id.templates_loading_no_content, find(R.id.templates_loading_no_content))
            visibilityCoordinator.add(R.id.templates_loading_error, loadingErrorView)
        }

        listContentView.run {
            layoutManager = LinearLayoutManager(context)
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        onLoadDecisionsListEvent()
    }

    private lateinit var loadingErrorView: TextView
    private lateinit var listContentView: RecyclerView

    private val onLoadDecisionsListEvent: NoticeInternalEvent = Events.basic()
}