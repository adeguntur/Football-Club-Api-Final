package com.ade.kotlin.presenter

import com.ade.kotlin.api.API
import com.ade.kotlin.api.ApiObject
import com.ade.kotlin.model.TeamResponse
import com.ade.kotlin.CCProvider
import com.ade.kotlin.view.TeamDetailInterface
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamDetailPresenter(private val anInterface: TeamDetailInterface,
                          private val apiRepository: API,
                          private val gson: Gson, private val context: CCProvider = CCProvider()) {

    fun getTeamDetail(teamId: String) {
        anInterface.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiObject.getDetailTeam(teamId)),
                        TeamResponse::class.java
                )
            }

            anInterface.showTeamDetail(data.await().teams)
            anInterface.hideLoading()
        }
    }
}