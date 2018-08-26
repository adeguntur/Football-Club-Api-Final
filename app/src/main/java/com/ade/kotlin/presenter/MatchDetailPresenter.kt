package com.ade.kotlin.presenter

import com.ade.kotlin.model.MatchEventResponse
import com.ade.kotlin.api.API
import com.ade.kotlin.api.ApiObject
import com.ade.kotlin.view.MatchInterface
import com.google.gson.Gson
import com.ade.kotlin.CCProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchDetailPresenter(private val anInterface: MatchInterface,
                           private val api: API,
                           private val gson: Gson,
                           private val context: CCProvider = CCProvider()) {

    fun getDetailMatch(event: String?) {
        anInterface.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(api
                        .doRequest(ApiObject.getDetailMatchEvent(event)),
                        MatchEventResponse::class.java
                )
            }
            anInterface.showMatchEventList(data.await().events)
            anInterface.hideLoading()
        }
    }
}