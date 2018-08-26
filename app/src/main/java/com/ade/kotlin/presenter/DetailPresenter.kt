package com.ade.kotlin.presenter

import com.ade.kotlin.model.MatchEventResponse
import com.ade.kotlin.api.API
import com.ade.kotlin.api.ApiObject
import com.ade.kotlin.view.DetailInterface
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPresenter(private val anInterface: DetailInterface,
                      private val API: API,
                      private val gson: Gson) {

    fun geDetailTeamList(teamA: String?, teamB: String?) {
        anInterface.showLoading()

        async(UI) {
            val dataA = bg {
                gson.fromJson(API
                        .doRequest(ApiObject.getDetailTeam(teamA)),
                        MatchEventResponse::class.java
                )
            }
            val dataB = bg {
                gson.fromJson(API
                        .doRequest(ApiObject.getDetailTeam(teamB)),
                        MatchEventResponse::class.java
                )
            }
            anInterface.showTeamsList(dataA.await().teams, dataB.await().teams)
            anInterface.hideLoading()
        }
    }
}
