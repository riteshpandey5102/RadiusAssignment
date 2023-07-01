package com.example.radiusassignment.di.module

import com.example.radiusassignment.data.models.propertymodel_realm
import com.example.radiusassignment.data.models.realmExclusion
import com.example.radiusassignment.data.models.realmExclusionList
import com.example.radiusassignment.data.models.realmFacilities
import com.example.radiusassignment.data.models.realmOptions
import com.example.radiusassignment.Constants.Constants
import com.example.radiusassignment.data.api.ApiHelper
import com.example.radiusassignment.data.api.ApiHelperImpl
import com.example.radiusassignment.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                propertymodel_realm::class,
                realmFacilities::class,
                realmExclusion::class,
                realmOptions::class,
                realmExclusionList::class
            )
        )
            .compactOnLaunch()
            .deleteRealmIfMigrationNeeded()
            .build()
        return Realm.open(config)
    }
}