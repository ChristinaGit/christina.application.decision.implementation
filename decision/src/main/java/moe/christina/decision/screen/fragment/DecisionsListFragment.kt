package moe.christina.decision.screen.fragment

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.christina.common.android.AndroidConstant
import moe.christina.common.android.coordination.LoadingViewVisibilityCoordinator
import moe.christina.decision.R
import moe.christina.decision.model.Decision
import moe.christina.decision.screen.DecisionsListScreen
import moe.christina.decision.screen.adapter.DecisionsListAdapter
import moe.christina.mvp.presenter.Presenter
import moe.christina.mvp.screen.behavior.ListScreenBehavior
import moe.christina.mvp.screen.behavior.ListScreenBehaviorDelegate
import moe.christina.mvp.screen.behavior.ListScreenBehaviorDelegate.ItemsRefresherFactory.asItemRefresher
import moe.christina.mvp.screen.behavior.ViewItemsEvent
import javax.inject.Inject

class DecisionsListFragment(private val listScreenDelegate: ListScreenBehaviorDelegate<Decision>)
    : BaseDecisionFragment(), DecisionsListScreen, ListScreenBehavior<Decision> by listScreenDelegate {
    companion object {
        val LOG_TAG = AndroidConstant.logTag<DecisionsListFragment>()

        @JvmStatic
        fun newInstance(): DecisionsListFragment {
            return DecisionsListFragment()
        }
    }

    constructor() : this(ListScreenBehaviorDelegate())

    private val decisionsAdapter = DecisionsListAdapter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_decisions_list, container, false)

        if (view != null) {
            listScreenDelegate.apply {
                visibilityCoordinator = LoadingViewVisibilityCoordinator().apply {
                    val itemsListView = view.findViewById(R.id.content) as RecyclerView
                    contentView = itemsListView

                    itemsListView.adapter = decisionsAdapter
                    itemsListView.layoutManager = LinearLayoutManager(view.context)

                    noContentView = view.findViewById(R.id.no_content)
                    loadingView = view.findViewById(R.id.loading)
                    errorView = view.findViewById(R.id.loading_error)
                }
                itemsRefresher = (view.findViewById(R.id.refresh) as? SwipeRefreshLayout)?.asItemRefresher()
                itemsConsumer = object : ListScreenBehaviorDelegate.ItemsConsumer<Decision> {
                    override fun setItems(items: List<Decision>?) {
                        Log.d(LOG_TAG, "2 items = $items")

                        decisionsAdapter.items = items
                        decisionsAdapter.notifyDataSetChanged()

                        listScreenDelegate.visibilityCoordinator!!.showContent(true)
                    }
                }
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        listScreenDelegate.riseViewItemsEvent(ViewItemsEvent.NEW)
    }

    @CallSuper
    override fun onInjectMembers() {
        super.onInjectMembers()

        decisionSubscreenComponent.inject(this)

        presenter.bindScreen(this)
    }

    @CallSuper
    override fun onReleaseInjectedMembers() {
        super.onReleaseInjectedMembers()

        presenter.unbindScreen()
    }

    @Inject
    lateinit var presenter: Presenter<DecisionsListScreen>
}
