package com.presentation.ui.recycler

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.domain.model.DataRow

/**
 * Created by Dmytro Pashko on 1/10/2019.
 */

class RecyclerViewModel : ViewModel() {

    val data = MutableLiveData<List<DataRow>>()

    init {
        data.postValue(IntRange(0, 25).toList().map { DataRow(it.toString()) })
    }
}
