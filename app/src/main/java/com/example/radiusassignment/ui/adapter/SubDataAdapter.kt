package com.example.radiusassignment.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.radiusassignment.data.models.Options
import com.example.radiusassignment.data.models.realmOptions
import com.example.radiusassignment.databinding.ListItemSubDataBinding
import com.example.radiusassignment.ui.viewmodels.sharedViewModel
import io.realm.kotlin.types.RealmList


class SubDataAdapter(
    val optionsList: RealmList<realmOptions>,
    val facilityId: String?,
    val sharedViewModel: sharedViewModel?
) :
    RecyclerView.Adapter<SubDataAdapter.ViewHolder>() {
    var lastSelectedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lifeCycleOwner = parent.context as LifecycleOwner
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemSubDataBinding =
            ListItemSubDataBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding,lifeCycleOwner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val op=optionsList.get(position)
        val options=Options(op.name,op.icon!!.replace("-","_"),op.id)
        holder.subDataBinding.itemViewModel=options
        holder.rbData.isChecked = lastSelectedPosition == position
        holder.rbData.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                sharedViewModel?.setId(facilityId!!, optionsList.get(position).id!!)
            }
        }
        setExclusion(holder,op.id)
    }

    fun setExclusion(holder : ViewHolder, optionID : String?){
        sharedViewModel?.getId()?.observe(holder.lifecycleOwner, Observer {map->
            if (map!=null){
                if ((map.containsValue("4") && optionID.equals("6")) ||
                    (map.containsValue("3") && optionID.equals("12")) ||
                    (map.containsValue("7") && optionID.equals("12"))
                ){
                    holder.rbData.isChecked = false
                    holder.tvData.visibility=View.VISIBLE
                    holder.rbData.visibility=View.GONE
                }
                else{
                    holder.rbData.isEnabled=true
                    holder.rbData.visibility=View.VISIBLE
                    holder.tvData.visibility=View.GONE
                }
            }
        })
    }

    override fun getItemCount(): Int {
        return optionsList.size
    }

    inner class ViewHolder(@NonNull subDataBinding: ListItemSubDataBinding, lifecycleOwner: LifecycleOwner) :
        RecyclerView.ViewHolder(subDataBinding.getRoot()) {
        var subDataBinding : ListItemSubDataBinding
        var lifecycleOwner = lifecycleOwner
        var rbData=subDataBinding.rbData
        var tvData=subDataBinding.tvData

        init {
            this.subDataBinding = subDataBinding
            rbData.setOnClickListener {
                lastSelectedPosition=adapterPosition
                notifyDataSetChanged()
            }
        }
    }
}
