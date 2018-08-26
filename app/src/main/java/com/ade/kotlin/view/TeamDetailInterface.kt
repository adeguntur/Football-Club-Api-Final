package com.ade.kotlin.view

import com.ade.kotlin.model.Team

interface TeamDetailInterface {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}