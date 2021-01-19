package com.sagamore.testapplication.feature_main_list

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder
import com.sagamore.testapplication.service.ApiService
import com.sagamore.testapplication.service.data.EmployeeModel
import com.sagamore.testapplication.service.data.EmployeeResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author a.v.davtyan
 */
class ItemListPresenter(private val view: ItemListView) {

    lateinit var retrofit: Retrofit

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadEmployees() {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val gson = GsonBuilder()
            .setLenient()
            .create()
        retrofit = Retrofit.Builder()
            .baseUrl(ItemListActivity.BASE_URL)
            .client(
                OkHttpClient().newBuilder().addNetworkInterceptor { chain ->
                    val original: Request = chain.request()
                    Log.i("11111", "intercept: ${chain.request().url}")
                    chain.proceed(original);
                }
                    .addInterceptor(logging)
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        callEndpoints()
    }

    private fun callEndpoints() {

        val apiService: ApiService = retrofit.create(ApiService::class.java)

        val observable: Observable<EmployeeResponse> = apiService.loadList()
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { result -> result.employees }
            .subscribe(this::handleResults, this::handleError);
    }


    private fun handleResults(marketList: List<EmployeeModel>) {
        if (marketList.isNotEmpty()) {
            view.onDataLoaded(marketList)
        } else {
            view.onNotFound()
        }
    }

    private fun handleError(t: Throwable) {

        view.onError(t)
        Log.e("11111", "handleError: ${t.stackTrace}")
    }
}
