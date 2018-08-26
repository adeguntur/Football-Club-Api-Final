package com.ade.kotlin.presenter

import com.ade.kotlin.api.API
import com.ade.kotlin.api.ApiObject
import com.ade.kotlin.model.TeamResponse
import com.ade.kotlin.view.TeamsInterface
import com.ade.kotlin.CCProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter(private val anInterface: TeamsInterface,
                     private val API: API,
                     private val gson: Gson, private val context: CCProvider = CCProvider()) {

    fun getTeamList(league: String?, leagueSearch: String?) {
        anInterface.showLoading()
        if (leagueSearch == "empty_parameter") {
            async(context.main) {
                val data = bg {
                    gson.fromJson(API
                            .doRequest(ApiObject.getTeams(league)),
                            TeamResponse::class.java
                    )
                }
                anInterface.showTeamList(data.await().teams)
                anInterface.hideLoading()
            }

        } else if (league == "empty_parameter") {
            async(context.main) {
                val data = bg {
                    gson.fromJson(API
                            .doRequest(ApiObject.getTeamSearch(leagueSearch)),
                            TeamResponse::class.java
                    )
                }
                anInterface.showTeamList(data.await().teams)
                anInterface.hideLoading()
            }
        }
    }
}