package com.example.radiusassignment.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.radiusassignment.data.models.propertymodel_realm
import com.example.radiusassignment.data.remote.repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@HiltViewModel
class viewmodel @Inject constructor(
    private val repository: repository
) : ViewModel() {

    fun fetchData() : Single<propertymodel_realm> {
        return repository.saveDataRealmDB()
    }

    fun getRealmData() : Single<propertymodel_realm>{
        return repository.getDataRealmDB()
    }

}