package com.ade.kotlin.view

import com.ade.kotlin.model.Team

interface TeamsInterface {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}