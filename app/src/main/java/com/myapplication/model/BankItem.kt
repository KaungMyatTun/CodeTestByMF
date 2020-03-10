package com.myapplication.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BankItem (
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("logo_url") var logoUrl : String,
    @SerializedName("created_at") var createdDate : String,
    @SerializedName("updated_at") var updatedDate : String,
    @SerializedName("is_major") var majorFlag : Boolean
) : Serializable