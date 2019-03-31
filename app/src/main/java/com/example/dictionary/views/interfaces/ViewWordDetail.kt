package com.example.dictionary.views.interfaces

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.dictionary.pojos.InfoTranslation

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface ViewWordDetail : MvpView {
    fun showErrorMessage(message: String)
    fun updateValues(item: InfoTranslation)
}
