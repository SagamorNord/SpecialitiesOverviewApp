package com.sagamore.testapplication.feature_main_list

import com.sagamore.testapplication.service.data.EmployeeModel

interface ItemListView {

    fun onDataLoaded(list: List<EmployeeModel>)

    fun onNotFound()

    fun onError(t: Throwable)
}
