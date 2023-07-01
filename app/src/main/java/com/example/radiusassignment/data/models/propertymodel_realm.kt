package com.example.radiusassignment.data.models

import com.google.gson.annotations.SerializedName
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject


class propertymodel_realm : RealmObject {
    var realmfacilities : RealmList<realmFacilities> = realmListOf()
    var realmexclusions : RealmList<realmExclusionList> = realmListOf()

    fun getPropertyModel(propertymodel: propertymodel) : propertymodel_realm {
        val propertymodelRealm = propertymodel_realm()
        for (t in propertymodel.facilities){
            propertymodelRealm.realmfacilities.add(realmFacilities(t.name,t.facilityId,t.options))
        }
        for (t in propertymodel.exclusions){
            val realmExlusionList = realmExclusionList(t)
            propertymodelRealm.realmexclusions.add(realmExlusionList)
        }
        return propertymodelRealm
    }
}

class realmExclusionList() :RealmObject {
    var exlusionList : RealmList<realmExclusion> = realmListOf()
    constructor(exclusionList: ArrayList<Exclusions>) : this() {
        for (t in exclusionList){
            this@realmExclusionList.exlusionList.add(realmExclusion(t.facilityId,t.optionsId))
        }
    }
}

class realmFacilities() : RealmObject {
    @SerializedName("facility_id" ) var facilityId : String? = null
    @SerializedName("name" ) var name: String? = null
    var realmoptions: RealmList<realmOptions> = realmListOf()
    constructor(name:String?, facilityID: String?, options :ArrayList<Options>) : this() {
        this@realmFacilities.facilityId=facilityID
        this@realmFacilities.name=name
        for (op in options){
            realmoptions.add(realmOptions(op.name,op.icon,op.id))
        }
    }
}

class realmExclusion() : RealmObject {
    @SerializedName("facility_id" ) var facilityId : String? = null
    @SerializedName("options_id"  ) var optionsId  : String? = null
    constructor(facilityID: String?, optionsID:String?) : this(){
        this@realmExclusion.facilityId=facilityID
        this@realmExclusion.optionsId=optionsID
    }
}

class realmOptions() : RealmObject {
    @SerializedName("name" ) var name: String? = null
    @SerializedName("icon" ) var icon: String? = null
    @SerializedName("id"   ) var id: String? = null
    constructor(name: String?,icon: String?,id:String?) : this(){
        this@realmOptions.name=name
        this@realmOptions.icon=icon
        this@realmOptions.id=id
    }
}


