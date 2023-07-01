package com.example.radiusassignment.data.remote

import com.example.radiusassignment.data.models.propertymodel
import com.example.radiusassignment.data.models.propertymodel_realm
import com.example.radiusassignment.data.api.ApiHelper
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class repository @Inject constructor(private val apiHelper: ApiHelper, private val realm: Realm) {

    private fun fetchData(): Single<propertymodel> {
        return apiHelper.getProperties()
    }

    fun saveDataRealmDB() : Single<propertymodel_realm>{
        var propertymodelRealm : propertymodel_realm;
        fetchData()
            .subscribe(object: SingleObserver<propertymodel> {
                override fun onSubscribe(d: Disposable) {

                }
                override fun onError(e: Throwable) {
                }
                override fun onSuccess(t: propertymodel) {
                    propertymodelRealm = propertymodel_realm().getPropertyModel(t)
                    CoroutineScope(Dispatchers.IO).launch {
                        realm.write { copyToRealm(propertymodelRealm, UpdatePolicy.ALL) }
                    }
                }
            })
        return getDataRealmDB()
    }

    fun getDataRealmDB() : Single<propertymodel_realm>{
        val result=  realm.query<propertymodel_realm>().find()
        if (result.size>0){
            return Single.just(result.first())
        }
        else
            return saveDataRealmDB()
    }

}