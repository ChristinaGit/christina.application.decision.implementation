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
import moe.christina.decision.model.data.Layer
import moe.christina.decision.screen.DecisionsListScreen
import moe.christina.decision.screen.adapter.DecisionsListAdapter
import moe.christina.mvp.core.utility.asDataViewController
import moe.christina.mvp.core.utility.asLoadDataViewController
import moe.christina.mvp.core.utility.asRefreshDataViewController
import moe.christina.mvp.presenter.Presenter
import moe.christina.mvp.screen.behavior.LoadableScreenDelegate
import moe.christina.mvp.screen.behavior.RefreshableScreenBehavior
import moe.christina.mvp.screen.behavior.RefreshableScreenDelegate
import org.jetbrains.anko.find
import javax.inject.Inject

class DecisionsListFragment
@JvmOverloads
constructor(
    private val loadableScreenDelegate: RefreshableScreenDelegate<List<Layer>> = RefreshableScreenDelegate<List<Layer>>())
    : BaserDecisionFragment(),
    DecisionsListScreen,
    RefreshableScreenBehavior<List<Layer>> by loadableScreenDelegate {

    companion object {
        @JvmStatic
        private val LOG_TAG = AndroidConstant.logTag<DecisionsListFragment>()

        @JvmStatic
        fun newInstance(): DecisionsListFragment {
            return DecisionsListFragment()
        }
    }

    private val decisionsAdapter = DecisionsListAdapter()

    override fun onInjectMembers() {
        super.onInjectMembers()

        presenter.bindScreen(this)
    }

    override fun onReleaseInjectedMembers() {
        super.onReleaseInjectedMembers()

        presenter.unbindScreen()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_decisions_list, container, false)?.apply view@ {
            loadableScreenDelegate.apply {
                val visibilityCoordinator = LoadingViewVisibilityCoordinator().apply {
                    contentView = (find<RecyclerView>(R.id.content)).apply {
                        adapter = decisionsAdapter
                        layoutManager = LinearLayoutManager(context)
                    }

                    noContentView = find<View>(R.id.no_content)
                    loadingView = find<View>(R.id.loading)
                    errorView = find<View>(R.id.loading_error)

                    loadingVisibilityChanger = ProgressVisibilityChanger()
                }
                val swipeRefreshView = (findViewById(R.id.refresh) as SwipeRefreshLayout).apply {
                    setOnRefreshListener { riseRefreshDataEvent() }
                }

                dataConsumer = object : LoadableScreenDelegate.DataConsumer<List<Layer>> {
                    override val hasData: Boolean
                        get() = decisionsAdapter.items?.isNotEmpty() == true

                    override fun setData(data: List<Layer>?) {
                        decisionsAdapter.apply {
                            items = data
                            notifyInnerItemsChanged()
                        }
                    }

                    override fun isEmpty(data: List<Layer>?) = data?.isEmpty() == true
                }
                dataViewController = visibilityCoordinator.asDataViewController()
                loadDataViewController = visibilityCoordinator.asLoadDataViewController {
                    Snackbar.make(this@view, "Fail to load data! ${it ?: ""}", Snackbar.LENGTH_SHORT).show()
                }
                refreshDataViewController = swipeRefreshView.asRefreshDataViewController(
                    messageSetter = { Snackbar.make(this@view, "Fail to refresh data! ${it ?: ""}", Snackbar.LENGTH_SHORT).show() }
                )
            }
        }
    }

    @CallSuper
    override fun onResume() {
        super.onResume()

        loadableScreenDelegate.riseLoadDataEvent()
    }

    @field:[Inject]
    lateinit var presenter: Presenter<DecisionsListScreen>
}
