package christina.application.decision.presentation.core

import christina.library.android.architecture.mvp.presenter.Presenter

abstract class BaseDecisionPresenter<out Screen>(
    screen: Screen
) : Presenter<Screen>(screen)