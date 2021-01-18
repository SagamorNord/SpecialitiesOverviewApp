package com.sagamore.testapplication

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author a.v.davtyan
 */
data class SpecialtyModel(
    @SerializedName("specialty_id")
    val specialtyId: Int,

    @SerializedName("name")
    val name: String
) : Serializable
