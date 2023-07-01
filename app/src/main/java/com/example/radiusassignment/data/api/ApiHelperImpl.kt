package com.example.radiusassignment.data.api

import com.example.radiusassignment.data.models.propertymodel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) :
    ApiHelper {
    override fun getProperties(): Single<propertymodel> {
        return apiService.getproperties()
    }
}