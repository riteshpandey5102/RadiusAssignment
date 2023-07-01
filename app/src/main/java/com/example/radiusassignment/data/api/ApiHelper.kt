package com.example.radiusassignment.data.api

import com.example.radiusassignment.data.models.propertymodel
import io.reactivex.rxjava3.core.Single

interface ApiHelper {
    fun getProperties(): Single<propertymodel>

}