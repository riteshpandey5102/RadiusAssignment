package com.example.radiusassignment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.radiusassignment.data.models.propertymodel_realm
import com.example.radiusassignment.R
import com.example.radiusassignment.data.models.Facilities
import com.example.radiusassignment.data.models.Options
import com.example.radiusassignment.data.models.realmOptions
import com.example.radiusassignment.databinding.ListItemMainBinding
import com.example.radiusassignment.databinding.ListItemSubDataBinding
import com.example.radiusassignment.ui.viewmodels.sharedViewModel
import io.realm.kotlin.Realm

class MainAdapter internal constructor(
    val context: Context,
    val dataModel: propertymodel_realm,
    val sharedViewModel: sharedViewModel
) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lifeCycleOwner = parent.context as LifecycleOwner
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemMainBinding =
            ListItemMainBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding,lifeCycleOwner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val facilityId = dataModel.realmfacilities.get(position).facilityId
        val name = dataModel.realmfacilities.get(position).name
        val dataAdapter = SubDataAdapter(dataModel.realmfacilities.get(position).realmoptions,
        facilityId, sharedViewModel)
        val linearLayoutManager = LinearLayoutManager(context)
        holder.mainBinding.rvListMain.layoutManager = linearLayoutManager
        holder.mainBinding.adapter=dataAdapter
        holder.mainBinding.name=name
    }

    override fun getItemCount(): Int {
        return dataModel.realmfacilities.size
    }

    inner class ViewHolder(@NonNull mainBinding: ListItemMainBinding, lifecycleOwner: LifecycleOwner) :
        RecyclerView.ViewHolder(mainBinding.getRoot()) {
        var mainBinding : ListItemMainBinding
        var lifecycleOwner = lifecycleOwner
        var tvName=mainBinding.tvName

        init {
            this.mainBinding = mainBinding
        }
    }
}