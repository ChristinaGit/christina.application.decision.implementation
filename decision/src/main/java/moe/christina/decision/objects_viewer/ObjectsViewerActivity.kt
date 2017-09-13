package moe.christina.decision.objects_viewer

import android.os.Bundle
import android.view.View
import moe.christina.common.core.event.Event
import moe.christina.decision.R
import moe.christina.decision.core.BaseDecisionActivity
import moe.christina.decision.objects_list.ObjectsListFragment
import moe.christina.decision.objects_viewer.domain.model.CreatedObject
import moe.christina.mvp.presenter.Presenter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
import javax.inject.Inject

class ObjectsViewerActivity :
    BaseDecisionActivity(),
    ObjectsViewerScreen,
    AnkoLogger {
    override val taskBehavior: CreateObjectBehaviorHandler =
        object : CreateObjectBehaviorHandler() {
            override fun onDisplayResult(result: CreatedObject) {
                super.onDisplayResult(result)

                longToast("$result created!")
            }

            override fun onDisplayError(error: String) {
                super.onDisplayError(error)

                longToast("Error: $error!")
            }
        }

    override fun onInjectMembers() {
        super.onInjectMembers()

        presenter.bindScreen()
    }

    override fun onReleaseInjectedMembers() {
        super.onReleaseInjectedMembers()

        presenter.unbindScreen()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_objects_viewer)
        setSupportActionBar(find(R.id.toolbar))

        find<View>(R.id.fab).setOnClickListener(this::onViewClick)

        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, ObjectsListFragment.newInstance())
                .commit()
        }
    }

    private fun onViewClick(view: View) {
        when (view.id) {
            R.id.fab -> onFabClick()
        }
    }

    private fun onFabClick() {
        taskBehavior.riseRequestPerformTaskEvent(Event.empty())
    }

    @field:[Inject]
    lateinit var presenter: Presenter<ObjectsViewerScreen>
}