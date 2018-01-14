package christina.application.decision.presentation.decisions_list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import christina.application.decision.presentation.R
import christina.application.decision.presentation.core.BaseDecisionFragment
import christina.common.core.accessor.mutableAccessor
import christina.common.event.Events
import christina.common.event.core.NoticeEvent
import christina.common.event.core.NoticeInternalEvent
import christina.common.event.core.invoke
import christina.common.state_coordinator.basic.BasicStateCoordinator
import christina.common.state_coordinator.core.StateCoordinator
import christina.library.android.architecture.mvp.screen_view.ScreenView
import christina.library.android.architecture.mvp.screen_view.content.ContentScreenView
import christina.library.android.architecture.mvp.screen_view.task.InitialTaskScreenView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info

class DecisionsListFragment :
    BaseDecisionFragment(),
    DecisionsListScreen,
    AnkoLogger {
    override val onLoadDecisionsList: NoticeEvent
        get() = onLoadDecisionsListEvent

    override val decisionsListScreenView: InitialTaskScreenView<List<String?>, String> =
        object : InitialTaskScreenView<List<String?>, String> {
            override var visible: Boolean
                get() = visibilityCoordinator.getState(R.id.templates_list_content)
                set(value) = visibilityCoordinator.setState(R.id.templates_list_content, value)

            override fun display(content: List<String?>) {
                decisionsListAdapter.items = content
            }

            override val progressScreenView: ScreenView =
                object : ScreenView {
                    override var visible: Boolean
                        get() = visibilityCoordinator.getState(R.id.templates_loading_progress)
                        set(value) = visibilityCoordinator.setState(R.id.templates_loading_progress, value).also { info("loadingView $value") }
                }

            override val errorScreenView: ContentScreenView<String> =
                object : ContentScreenView<String> {
                    override var visible: Boolean
                        get() = visibilityCoordinator.getState(R.id.templates_loading_error)
                        set(value) = visibilityCoordinator.setState(R.id.templates_loading_error, value).also { info("errorView $value") }

                    override fun display(content: String) {
                        loadingErrorView.text = content
                    }
                }
        }

    private val visibilityCoordinator: StateCoordinator<Int, View, Boolean>
        = BasicStateCoordinator(mutableAccessor(
        { it.visibility == View.VISIBLE },
        { view, visible -> view.visibility = if (visible) View.VISIBLE else View.GONE }
    ))

    companion object {
        @JvmStatic
        fun newInstance(): DecisionsListFragment = DecisionsListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::decisionsListAdapter.isInitialized) {
            decisionsListAdapter = DecisionsListAdapter(inflater)
        }

        val view = inflater.inflate(R.layout.tempaltes_refreshable_list, container, false)

        view.run {
            listContentView = find(R.id.templates_list_content)
            loadingErrorView = find(R.id.templates_loading_error)

            visibilityCoordinator.add(R.id.templates_list_content, listContentView)
            visibilityCoordinator.add(R.id.templates_loading_progress, find(R.id.templates_loading_progress))
            visibilityCoordinator.add(R.id.templates_loading_no_content, find(R.id.templates_loading_no_content))
            visibilityCoordinator.add(R.id.templates_loading_error, loadingErrorView)

            info("reset")
            visibilityCoordinator.setState(false)
        }

        listContentView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = decisionsListAdapter
        }

        //        visibilityCoordinator[R.id.templates_loading_error] = true
        //        loadingErrorView.setOnClickListener {
        //            visibilityCoordinator[R.id.templates_loading_progress] = !visibilityCoordinator[R.id.templates_loading_progress]
        //
        //            info("click -> ${visibilityCoordinator[R.id.templates_loading_progress]}")
        //        }

        return view
    }

    override fun onResume() {
        super.onResume()

        onLoadDecisionsListEvent()
    }

    private lateinit var loadingErrorView: TextView
    private lateinit var listContentView: RecyclerView

    private lateinit var decisionsListAdapter: DecisionsListAdapter

    private val onLoadDecisionsListEvent: NoticeInternalEvent = Events.basic()
}