package moe.christina.decision.core

import moe.christina.mvp.presenter.BasePresenter

abstract class BaseDecisionPresenter<out TScreen>(
    screen: TScreen
) : BasePresenter<TScreen>(screen)