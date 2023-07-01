
package com.example.radiusassignment.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.radiusassignment.R
import com.example.radiusassignment.data.models.propertymodel_realm
import com.example.radiusassignment.databinding.ActivityMainBinding
import com.example.radiusassignment.ui.adapter.MainAdapter
import com.example.radiusassignment.ui.viewmodels.sharedViewModel
import com.example.radiusassignment.ui.viewmodels.viewmodel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewmodel : viewmodel by viewModels()
    val sharedViewModel : sharedViewModel by viewModels()

    var rvListMain: RecyclerView? = null
    var linearLayoutManager: LinearLayoutManager? = null
    var mainAdapter: MainAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )

        rvListMain = binding.rvListMain
        linearLayoutManager = LinearLayoutManager(this)
        rvListMain!!.layoutManager=linearLayoutManager

        viewmodel.getRealmData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<propertymodel_realm>{
                override fun onSubscribe(d: Disposable) {
                }
                override fun onError(e: Throwable) {
                }
                override fun onSuccess(t: propertymodel_realm) {
                    mainAdapter = MainAdapter(this@MainActivity, t,sharedViewModel)
                    rvListMain!!.adapter=mainAdapter
                }
            })

    }
}