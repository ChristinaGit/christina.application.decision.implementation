package moe.christina.decision.screen.activity

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import moe.christina.common.android.AndroidConstant
import moe.christina.common.core.Event
import moe.christina.decision.R
import moe.christina.decision.screen.DecisionsViewerScreen
import moe.christina.decision.screen.fragment.DecisionsListFragment
import moe.christina.mvp.presenter.Presenter
import org.jetbrains.anko.find
import javax.inject.Inject

class DecisionsViewerActivity : BaseDecisionActivity(), DecisionsViewerScreen {
    companion object {
        @JvmStatic
        private val LOG_TAG = AndroidConstant.logTag<DecisionsViewerActivity>()
        @JvmStatic
        private val SAVED_STATE_KEY = AndroidConstant.savedStateKey<DecisionsViewerActivity>("saved_state")
    }

    override fun displayMessage(message: String) {
        Toast.makeText(this, "Message: $message", Toast.LENGTH_SHORT).show()
    }

    override val onCreateDecision: Observable<Event>
        get() = onCreateDecisionSubject.hide()

    override fun onInjectMembers() {
        super.onInjectMembers()

        presenter.bindScreen(this)
    }

    override fun onReleaseInjectedMembers() {
        super.onReleaseInjectedMembers()

        presenter.unbindScreen()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_decisions_viewer)
        setSupportActionBar(find<Toolbar>(R.id.toolbar))

        fabView = find<FloatingActionButton>(R.id.fab)
        fabView.setOnClickListener(viewClickListener)

        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, DecisionsListFragment.newInstance())
                .commit()
        }
    }

    @CallSuper
    protected fun onFabClick() = riseOnCreateDecisionEvent()

    private val viewClickListener: View.OnClickListener = View.OnClickListener {
        when (it?.id) {
            R.id.fab -> onFabClick()
        }
    }

    private lateinit var fabView: FloatingActionButton

    @field:[Inject]
    lateinit var presenter: Presenter<DecisionsViewerScreen>

    private fun riseOnCreateDecisionEvent() =
        onCreateDecisionSubject.onNext(Event.empty)

    private val onCreateDecisionSubject: Subject<Event> = PublishSubject.create()
}