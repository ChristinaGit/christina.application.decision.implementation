package christina.application.decision.objects_list

import android.os.Bundle
import android.support.annotation.CallSuper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import christina.common.event.Events
import christina.common.event.core.NoticeEvent
import christina.common.event.core.NoticeInternalEvent
import christina.common.event.core.invoke
import moe.christina.decision.R
import christina.application.decision.core.BaseDecisionFragment
import christina.application.decision.objects_list.domain.adapter.ObjectsListAdapter
import christina.application.decision.objects_list.domain.model.Object
import christina.library.android.architecture.mvp.screen_view.task.TaskScreenView
import org.jetbrains.anko.AnkoLogger

class ObjectsListFragment :
    BaseDecisionFragment(),
    ObjectsListScreen,
    AnkoLogger {
    companion object {
        @JvmStatic
        fun newInstance(): ObjectsListFragment = ObjectsListFragment()
    }

    override val objectsListScreenView: TaskScreenView<List<Object>, String>
        get() = TODO("not implemented")

    override val onLoadObjectsList: NoticeEvent
        get() = onLoadObjectsListEvent

    private val objectsAdapter = ObjectsListAdapter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.tempaltes_refreshable_list, container, false)?.apply view@ {
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
            //                        getAll() = objectsAdapter.items?.isNotEmpty() == true
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
            //                    Snackbar.make(this@view, "Fail to getAll data! ${it ?: ""}", Snackbar.LENGTH_SHORT).show()
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

        onLoadObjectsListEvent()
    }

    private val onLoadObjectsListEvent: NoticeInternalEvent = Events.basic()
}