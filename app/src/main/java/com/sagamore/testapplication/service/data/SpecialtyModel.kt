package com.sagamore.testapplication.service.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author a.v.davtyan
 */
data class SpecialtyModel(
    @SerializedName("specialty_id") val specialty_id: Int,
    @SerializedName("name") val name: String
) : Serializable
