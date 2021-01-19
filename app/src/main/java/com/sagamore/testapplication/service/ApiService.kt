package com.sagamore.testapplication.service

import com.sagamore.testapplication.service.data.EmployeeResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author a.v.davtyan
 */
interface ApiService {

    @GET("65gb/static/raw/master/testTask.json")
    fun loadList(): Observable<EmployeeResponse>
}
