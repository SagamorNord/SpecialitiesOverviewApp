package com.sagamore.testapplication

import retrofit2.Call
import retrofit2.http.GET

/**
 * @author a.v.davtyan
 */
interface ApiService {

    @GET("https://gitlab.65apps.com/65gb/static/raw/master/testTask.json")
    fun loadList(): Call<EmployeeModel>
}
