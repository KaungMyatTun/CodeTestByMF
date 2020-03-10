package com.myapplication.model

import com.google.gson.annotations.SerializedName

data class BankResponse(
    @SerializedName("data") val BankResponse: List<BankItem?>? = null
)