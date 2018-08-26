package com.ade.kotlin.presenter

import com.ade.kotlin.api.ApiObject
import com.ade.kotlin.api.API
import com.ade.kotlin.view.MatchInterface
import com.ade.kotlin.CCProvider
import com.ade.kotlin.model.MatchEventResponse
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextMatchPresenter(private val anInterface: MatchInterface,
                         private val API: API,
                         private val gson: Gson,
                         private val context: CCProvider = CCProvider()) {

    fun getMatchList(match: String?) {
        anInterface.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(API
                        .doRequest(ApiObject.getNextMatchEvent(match)),
                        MatchEventResponse::class.java
                )
            }
            anInterface.showMatchEventList(data.await().events)
            anInterface.hideLoading()
        }
    }
}
