package com.ade.kotlin.view

import com.ade.kotlin.model.PlayerTeam

interface PemainTeamInterface {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<PlayerTeam>)
}