package com.example.radiusassignment.data.api

import com.example.radiusassignment.data.models.propertymodel
import com.example.radiusassignment.Constants.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.GET_PROPERTY_URL)
    fun getproperties(): Single<propertymodel>
}