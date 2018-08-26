package com.ade.kotlin

import com.ade.kotlin.api.API
import com.ade.kotlin.api.ApiObject
import com.ade.kotlin.model.MatchEvent
import com.ade.kotlin.model.MatchEventResponse
import com.ade.kotlin.presenter.MatchDetailPresenter
import com.ade.kotlin.view.MatchInterface
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchDetailTest {
    @Test
    fun testDetailMatch() {
        val Team: MutableList<MatchEvent> = mutableListOf()
        val Response = MatchEventResponse(Team)
        val event = "Spanish La Liga"

        Mockito.`when`(gson.fromJson(api
                .doRequest(ApiObject.getDetailMatchEvent(event)),
                Response::class.java
        )).thenReturn(Response)

        matchDetail.getDetailMatch(event)

        Mockito.verify(anInterface).showLoading()
        Mockito.verify(anInterface).showMatchEventList(Team)
        Mockito.verify(anInterface).hideLoading()
    }

    @Mock
    private lateinit var anInterface: MatchInterface
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var api: API
    @Mock
    private lateinit var matchDetail: MatchDetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        matchDetail = MatchDetailPresenter(anInterface, api, gson, TCProvider())
    }
}