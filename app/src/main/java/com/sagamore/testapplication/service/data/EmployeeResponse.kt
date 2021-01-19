package com.sagamore.testapplication.service.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * @author a.v.davtyan
 */
data class EmployeeResponse(
    @SerializedName("response")
    val employees: List<EmployeeModel>
) : Serializable
