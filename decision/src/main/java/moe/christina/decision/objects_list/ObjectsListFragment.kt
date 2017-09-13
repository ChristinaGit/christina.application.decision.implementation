package moe.christina.decision.objects_list

import android.os.Bundle
import android.support.annotation.CallSuper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moe.christina.common.core.event.Event
import moe.christina.decision.R
import moe.christina.decision.core.BaseDecisionFragment
import moe.christina.decision.objects_list.domain.adapter.ObjectsListAdapter
import moe.christina.decision.objects_viewer.domain.model.CreatedObject
import moe.christina.mvp.presenter.Presenter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.longToast
import javax.inject.Inject

class ObjectsListFragment
    : BaseDecisionFragment(),
    ObjectsListScreen,
    AnkoLogger {

    companion object {
        @JvmStatic
        fun newInstance(): ObjectsListFragment = ObjectsListFragment()
    }

    override val taskBehavior: LoadObjectsBehaviorHandler =
        object : LoadObjectsBehaviorHandler() {
            override fun onDisplayResult(result: List<CreatedObject>) {
                super.onDisplayResult(result)

                activity.longToast("$result created!")
            }

            override fun onDisplayError(error: String) {
                super.onDisplayError(error)

                activity.longToast("Error: $error!")
            }
        }

    private val objectsAdapter = ObjectsListAdapter()

    override fun onInjectMembers() {
        super.onInjectMembers()

        presenter.bindScreen()
    }

    override fun onReleaseInjectedMembers() {
        super.onReleaseInjectedMembers()

        presenter.unbindScreen()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_objects_list, container, false)?.apply view@ {
            //            loadableScreenDelegate.apply {
            //                val visibilityCoordinator = LoadingVisibilityCoordinator().apply {
            //                    addContent(find<RecyclerView>(R.id.content).apply {
            //                        adapter = objectsAdapter
            //                        layoutManager = LinearLayoutManager(context)
            //                    })
            //                    addNoContent(find(R.id.no_content))
            //                    addLoading(find(R.id.loading))
            //                    addError(find(R.id.loading_error))
            //
            //                    loadingVisibilityChanger = LoadingVisibilityChanger()
            //
            //                    setState(false)
            //                }
            //                val swipeRefreshView = (findViewById(R.id.refresh) as SwipeRefreshLayout).apply {
            //                    setOnRefreshListener { riseRefreshDataEvent() }
            //                }
            //
            //                dataConsumer = object : LoadableScreenBehaviorBase.DataConsumer<List<Object>> {
            //                    override val hasData: Boolean
            //                        get() = objectsAdapter.items?.isNotEmpty() == true
            //
            //                    override fun setData(data: List<Object>?) {
            //                        objectsAdapter.apply {
            //                            items = data
            //                            notifyDataSetChanged()
            //                        }
            //                    }
            //
            //                    override fun isEmpty(data: List<Object>?) = data?.isEmpty() == true
            //                }
            //                dataViewController = visibilityCoordinator.asDataViewController()
            //                loadDataViewController = visibilityCoordinator.asLoadDataViewController {
            //                    Snackbar.make(this@view, "Fail to load data! ${it ?: ""}", Snackbar.LENGTH_SHORT).show()
            //                }
            //                refreshDataViewController = swipeRefreshView.asRefreshDataViewController(
            //                    messageSetter = { Snackbar.make(this@view, "Fail to refresh data! ${it ?: ""}", Snackbar.LENGTH_SHORT).show() }
            //                )
            //            }
        }
    }

    @CallSuper
    override fun onResume() {
        super.onResume()

        taskBehavior.riseRequestPerformTaskEvent(Event.empty())
    }

    @field:[Inject]
    lateinit var presenter: Presenter<ObjectsListScreen>
}