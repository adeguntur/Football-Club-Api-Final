package com.ade.kotlin.view

import com.ade.kotlin.model.Team

interface DetailInterface {
    fun showLoading()
    fun hideLoading()
    fun showTeamsList(data: List<Team>?, data2: List<Team>?)
}