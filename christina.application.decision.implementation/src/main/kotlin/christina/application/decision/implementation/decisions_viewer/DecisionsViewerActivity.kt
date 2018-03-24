package christina.application.decision.implementation.decisions_viewer

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import christina.application.decision.implementation.core.BaseDecisionActivity
import christina.application.decision.implementation.decisions_list.DecisionsListFragment
import christina.application.decision.implementation.R
import christina.application.decision.presentation.decisions_viewer.DecisionsViewerScreen
import christina.application.decision.presentation.decisions_viewer.model.CreatedDecisionModel
import christina.common.rx.event.UnitEvent
import christina.common.rx.event.invoke
import christina.library.android.architecture.mvp.screen_view.content.ContentScreenView
import christina.library.android.common.AndroidConstant
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.parceler.Parcel
import org.parceler.Parcels

class DecisionsViewerActivity :
    BaseDecisionActivity(),
    DecisionsViewerScreen,
    AnkoLogger {
    companion object {
        private val _KEY_STATE = AndroidConstant.getSavedStateKey<DecisionsViewerActivity>("state")
    }

    override val onRequestCreateDecision: Observable<UnitEvent>
        get() = onRequestCreateDecisionEvent.hide()

    override val createdDecisionScreenView: ContentScreenView<CreatedDecisionModel> =
        object : ContentScreenView<CreatedDecisionModel> {
            private var snackbar: Snackbar? = null

            override var visible: Boolean
                get() = snackbar?.isShownOrQueued == true
                set(value) {
                    snackbar?.dismiss()

                    snackbar = null
                }

            override fun display(content: CreatedDecisionModel) {
                snackbar = Snackbar
                    .make(coordinatorView, content.message, Snackbar.LENGTH_LONG)
                    .apply(Snackbar::show)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        state = if (savedInstanceState !== null) {
            Parcels.unwrap(savedInstanceState.getParcelable(_KEY_STATE))
                ?: DecisionsViewerActivityState()
        } else {
            val action = when (intent.action) {
                Intent.ACTION_VIEW,
                Intent.ACTION_MAIN           -> Action.VIEW
                Intent.ACTION_INSERT         -> Action.INSERT
                Intent.ACTION_INSERT_OR_EDIT -> Action.INSERT_OR_EDIT
                Intent.ACTION_PICK           -> if (intent.getBooleanExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)) Action.PICK_MANY else Action.PICK
                Intent.ACTION_EDIT           -> Action.EDIT
                else                         -> Action.NONE
            }

            DecisionsViewerActivityState().apply {
                this.action = action
            }
        }

        if (state.action === Action.NONE) {
            toast(R.string.decisions_viewer_error_unsupported_action)

            finish()
        } else {
            setContentView(R.layout.templates_viewer)
            setSupportActionBar(find(R.id.templates_toolbar))

            coordinatorView = find(R.id.templates_viewer_coordinator)
            find<View>(R.id.templates_viewer_fab).setOnClickListener(::onViewClick)

            if (supportFragmentManager.findFragmentById(R.id.templates_viewer_fragment_container) == null) {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.templates_viewer_fragment_container, DecisionsListFragment.newInstance())
                    .commit()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.apply {
            putParcelable(_KEY_STATE, Parcels.wrap(state))
        }
    }

    private var state: DecisionsViewerActivityState = DecisionsViewerActivityState()

    private fun onViewClick(view: View) {
        when (view.id) {
            R.id.templates_viewer_fab -> onFabClick()
        }
    }

    private fun onFabClick() {
        onRequestCreateDecisionEvent()
    }

    private lateinit var coordinatorView: View

    private val onRequestCreateDecisionEvent: Subject<UnitEvent> = PublishSubject.create()

    enum class Action {
        NONE,
        VIEW,
        INSERT,
        INSERT_OR_EDIT,
        PICK,
        PICK_MANY,
        EDIT
    }

    @Parcel(Parcel.Serialization.BEAN)
    class DecisionsViewerActivityState {
        var action: Action = Action.NONE
    }
}