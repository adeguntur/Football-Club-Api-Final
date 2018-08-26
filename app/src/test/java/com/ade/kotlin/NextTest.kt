package com.ade.kotlin

import com.ade.kotlin.api.API
import com.ade.kotlin.api.ApiObject
import com.ade.kotlin.model.MatchEvent
import com.ade.kotlin.model.MatchEventResponse
import com.ade.kotlin.presenter.NextMatchPresenter
import com.ade.kotlin.view.MatchInterface
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextTest {
    @Test
    fun getNextMatchList() {
        val Team: MutableList<MatchEvent> = mutableListOf()
        val Response = MatchEventResponse(Team)
        val League = "Spanish La Liga"

        Mockito.`when`(gson.fromJson(api
                .doRequest(ApiObject.getNextMatchEvent(League)),
                Response::class.java
        )).thenReturn(Response)

        next.getMatchList(League)

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
    private lateinit var next: NextMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        next = NextMatchPresenter(anInterface, api, gson, TCProvider())
    }

}