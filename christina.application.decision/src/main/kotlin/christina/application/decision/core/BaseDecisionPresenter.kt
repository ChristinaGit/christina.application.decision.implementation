package christina.application.decision.core

import christina.library.android.architecture.mvp.presenter.Presenter

abstract class BaseDecisionPresenter<out TScreen>(
    screen: TScreen
) : Presenter<TScreen>(screen)