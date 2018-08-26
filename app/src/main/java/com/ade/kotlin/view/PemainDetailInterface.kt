package com.ade.kotlin.view

import com.ade.kotlin.model.PlayerDetail

interface PemainDetailInterface {
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data: List<PlayerDetail>)
}