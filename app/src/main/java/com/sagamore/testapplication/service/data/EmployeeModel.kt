package com.sagamore.testapplication.service.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author a.v.davtyan
 */
data class EmployeeModel(
    @SerializedName("f_name") val f_name: String,
    @SerializedName("l_name") val l_name: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("avatr_url") val avatr_url: String,
    @SerializedName("specialty") val specialty: List<SpecialtyModel>
) : Serializable
