package com.ade.kotlin.api

import java.net.URL

class API {

    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}