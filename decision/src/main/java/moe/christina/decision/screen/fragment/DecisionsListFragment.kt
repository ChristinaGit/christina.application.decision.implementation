package moe.christina.decision.screen.fragment

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.christina.common.android.AndroidConstant
import moe.christina.common.android.coordination.LoadingViewVisibilityCoordinator
import moe.christina.common.android.coordination.visibility.ProgressVisibilityChanger
import moe.christina.decision.R
import moe.christina.decision.di.qualifier.ScreenName
import moe.christina.decision.model.data.Decision
import moe.christina.decision.screen.DecisionsListScreen
import moe.christina.decision.screen.adapter.DecisionsListAdapter
import moe.christina.mvp.core.utility.asDataViewController
import moe.christina.mvp.core.utility.asLoadDataViewController
import moe.christina.mvp.core.utility.asRefreshDataViewController
import moe.christina.mvp.presenter.Presenter
import moe.christina.mvp.screen.behavior.RefreshableScreenBehavior
import moe.christina.mvp.screen.behavior.RefreshableScreenDelegate
import javax.inject.Inject
import javax.inject.Named

class DecisionsListFragment(val loadableScreenDelegate: RefreshableScreenDelegate<List<Decision>>)
    : BaseDecisionFragment(), DecisionsListScreen, RefreshableScreenBehavior<List<Decision>> by loadableScreenDelegate {
    companion object {
        @JvmStatic
        private val LOG_TAG = AndroidConstant.logTag<DecisionsListFragment>()

        @JvmStatic
        fun newInstance(): DecisionsListFragment {
            return DecisionsListFragment()
        }
    }

    constructor() : this(RefreshableScreenDelegate<List<Decision>>())

    private val decisionsAdapter = DecisionsListAdapter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_decisions_list, container, false)?.apply view@ {
            loadableScreenDelegate.apply {
                val visibilityCoordinator = LoadingViewVisibilityCoordinator().apply {
                    contentView = (findViewById(R.id.content) as RecyclerView).apply {
                        adapter = decisionsAdapter
                        layoutManager = LinearLayoutManager(context)
                    }

                    noContentView = findViewById(R.id.no_content)
                    loadingView = findViewById(R.id.loading)
                    errorView = findViewById(R.id.loading_error)

                    loadingVisibilityChanger = ProgressVisibilityChanger()
                }
                val swipeRefreshView = (findViewById(R.id.refresh) as SwipeRefreshLayout).apply {
                    setOnRefreshListener { riseRefreshDataEvent() }
                }

                setDataConsumer(
                    { decisionsAdapter.items?.isNotEmpty() == true },
                    {
                        decisionsAdapter.apply {
                            items = it
                            notifyInnerItemsChanged()
                        }
                    },
                    { it?.isEmpty() == true })
                dataViewController = visibilityCoordinator.asDataViewController()
                loadDataViewController = visibilityCoordinator.asLoadDataViewController()
                refreshDataViewController = swipeRefreshView.asRefreshDataViewController { visible ->
                    if (visible)
                        Snackbar.make(this@view, "Fail to refresh data!", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    @CallSuper
    override fun onResume() {
        super.onResume()

        loadableScreenDelegate.riseLoadDataEvent()
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

    @field:[Inject Named(ScreenName.DECISIONS_LIST)]
    lateinit var presenter: Presenter<DecisionsListScreen>
}
