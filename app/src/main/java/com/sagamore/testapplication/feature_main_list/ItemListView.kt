package com.sagamore.testapplication

interface ItemListView {

    fun onDataLoaded(list: List<EmployeeModel>)

    fun onNotFound()

    fun onError(t: Throwable)

}
