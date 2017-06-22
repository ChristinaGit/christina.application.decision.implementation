package moe.christina.decision.screen.activity

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import moe.christina.common.android.AndroidConstant
import moe.christina.decision.R
import moe.christina.decision.screen.DecisionsViewerScreen
import moe.christina.decision.screen.fragment.DecisionsListFragment
import moe.christina.mvp.presenter.Presenter
import javax.inject.Inject

class DecisionsViewerActivity : BaseDecisionActivity(), DecisionsViewerScreen {
    companion object {
        val LOG_TAG = AndroidConstant.logTag<DecisionsViewerActivity>()
        val SAVED_STATE_KEY = AndroidConstant.savedStateKey<DecisionsViewerActivity>("saved_state")
    }

    override fun displayMessage(message: String) {
        Toast.makeText(this, "Message: $message", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_decisions_viewer)
        setSupportActionBar(findViewById(R.id.toolbar) as Toolbar)

        fabView = findViewById(R.id.fab) as FloatingActionButton
        fabView.setOnClickListener(viewClickListener)

        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, DecisionsListFragment.newInstance())
                    .commit()
        }
    }

    @CallSuper
    override fun onInjectMembers() {
        super.onInjectMembers()

        decisionScreenComponent.inject(this)

        presenter.bindScreen(this)
    }

    @CallSuper
    override fun onReleaseInjectedMembers() {
        super.onReleaseInjectedMembers()

        presenter.unbindScreen()
    }

    @CallSuper
    protected fun onFabClick() {
        Snackbar.make(fabView, "Reified types test : $SAVED_STATE_KEY", Snackbar.LENGTH_LONG).show()
    }

    @Inject
    lateinit var presenter: Presenter<DecisionsViewerScreen>

    private val viewClickListener: View.OnClickListener = View.OnClickListener { v ->
        when (v?.id) {
            R.id.fab -> onFabClick()
        }
    }

    private lateinit var fabView: FloatingActionButton
}