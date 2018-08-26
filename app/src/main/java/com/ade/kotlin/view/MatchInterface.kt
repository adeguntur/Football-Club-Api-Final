package com.ade.kotlin.view

import com.ade.kotlin.model.MatchEvent

interface MatchInterface {
    fun showLoading()
    fun hideLoading()
    fun showMatchEventList(match: List<MatchEvent>?)
}