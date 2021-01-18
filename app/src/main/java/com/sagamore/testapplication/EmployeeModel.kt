package com.sagamore.testapplication

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author a.v.davtyan
 */
data class EmployeeModel(
    @SerializedName("f_name")
    val firstName: String,

    @SerializedName("l_name")
    val lastName: String,

    @SerializedName("birthday")
    val birthday: Date,

    @SerializedName("avatr_url")
    val avatrUrl: String,

    @SerializedName("specialty")
    val specialty: ArrayList<SpecialtyModel>
) : Serializable
