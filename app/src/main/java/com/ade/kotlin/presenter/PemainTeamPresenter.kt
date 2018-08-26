package com.ade.kotlin.presenter

import com.ade.kotlin.api.API
import com.ade.kotlin.api.ApiObject
import com.ade.kotlin.model.PlayerTeamResponse
import com.ade.kotlin.CCProvider
import com.ade.kotlin.view.PemainTeamInterface
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PemainTeamPresenter(private val anInterface: PemainTeamInterface,
                          private val apiRepository: API,
                          private val gson: Gson, private val context:
                          CCProvider = CCProvider()) {

    fun getPlayerList(idTeam: String?) {
        anInterface.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(ApiObject.getPlayerTeam(idTeam)),
                        PlayerTeamResponse::class.java
                )
            }
            anInterface.showPlayerList(data.await().playerTeams)
            anInterface.hideLoading()
        }
    }
}