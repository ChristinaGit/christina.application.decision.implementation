package christina.application.decision.implementation.decisions_list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import christina.application.decision.implementation.core.BaseDecisionFragment
import christina.application.decision.implementation.R
import christina.application.decision.presentation.decisions_list.DecisionsListScreen
import christina.common.core.accessor.mutableAccessor
import christina.common.rx.event.UnitEvent
import christina.common.rx.event.invoke
import christina.common.state_coordinator.basic.BasicStateCoordinator
import christina.common.state_coordinator.core.StateCoordinator
import christina.library.android.architecture.mvp.screen_view.ScreenView
import christina.library.android.architecture.mvp.screen_view.content.ContentScreenView
import christina.library.android.architecture.mvp.screen_view.task.IndeterminateTaskScreenView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find

class DecisionsListFragment :
    BaseDecisionFragment(),
    DecisionsListScreen,
    AnkoLogger {
    override val onRequestLoadDecisionsList: Observable<UnitEvent>
        get() = onRequestLoadDecisionsListEvent.hide()

    override val decisionsListScreenView: IndeterminateTaskScreenView<List<String?>, Unit> =
        object : IndeterminateTaskScreenView<List<String?>, Unit> {
            override var visible: Boolean
                get() = visibilityCoordinator.getState(R.id.templates_list_content)
                set(value) = visibilityCoordinator.setState(R.id.templates_list_content, value)

            override fun display(content: List<String?>) {
                decisionsListAdapter.apply {
                    items = content
                    notifyDataSetChanged()
                }
            }

            override val progressScreenView: ScreenView =
                object : ScreenView {
                    override var visible: Boolean
                        get() = visibilityCoordinator.getState(R.id.templates_loading_progress)
                        set(value) = visibilityCoordinator.setState(R.id.templates_loading_progress, value)
                }

            override val errorScreenView: ContentScreenView<Unit> =
                object : ContentScreenView<Unit> {
                    override var visible: Boolean
                        get() = visibilityCoordinator.getState(R.id.templates_loading_error)
                        set(value) = visibilityCoordinator.setState(R.id.templates_loading_error, value)

                    override fun display(content: Unit) {
                    }
                }
        }

    private val visibilityCoordinator: StateCoordinator<Int, View, Boolean> =
        BasicStateCoordinator(
            mutableAccessor(
                { it.visibility == View.VISIBLE },
                { view, visible -> view.visibility = if (visible) View.VISIBLE else View.GONE }
            )
        )

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

            visibilityCoordinator.setState(false)
        }

        listContentView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = decisionsListAdapter
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        onRequestLoadDecisionsListEvent()
    }

    private lateinit var loadingErrorView: TextView
    private lateinit var listContentView: RecyclerView

    private lateinit var decisionsListAdapter: DecisionsListAdapter

    private val onRequestLoadDecisionsListEvent: Subject<UnitEvent> = PublishSubject.create()
}