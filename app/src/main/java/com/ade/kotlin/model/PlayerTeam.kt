package com.ade.kotlin.model

import com.google.gson.annotations.SerializedName

data class PlayerTeam(
        @SerializedName("idPlayer")
        var idPlayer: String? = null,

        @SerializedName("idTeam")
        var teamId: String? = null,

        @SerializedName("strCutout")
        var strCutout: String? = null,

        @SerializedName("strPlayer")
        var strPlayer: String? = null,

        @SerializedName("strPosition")
        var strPosition: String? = null
)