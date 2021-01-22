package com.sagamore.testapplication.feature_main_list

import com.sagamore.testapplication.service.data.EmployeeModel
import com.sagamore.testapplication.service.data.SpecialtyModel

interface ItemListView {

    fun onDataLoaded(list: List<SpecialtyModel>)

    fun onEmployeeLoaded(list: List<EmployeeModel>)

    fun onNotFound()

    fun onError(t: Throwable)
}
