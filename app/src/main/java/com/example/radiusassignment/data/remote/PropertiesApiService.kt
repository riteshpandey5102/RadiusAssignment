package com.example.radiusassignment.data.remote

import com.example.radiusassignment.data.models.propertymodel
import com.example.radiusassignment.Constants.Constants
import com.example.radiusassignment.data.api.ApiService
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PropertiesApiService {

    companion object{
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun getData():Single<propertymodel>{
        return retrofit.getproperties()
    }
}