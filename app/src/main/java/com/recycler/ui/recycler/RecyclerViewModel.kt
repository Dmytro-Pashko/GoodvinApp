package com.recycler.ui.recycler

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.recycler.model.DataRow

class RecyclerViewModel : ViewModel() {

    val data = MutableLiveData<List<DataRow>>()

    init {
        data.postValue(IntRange(0, 100).toList().map { DataRow(it.toString()) })
    }
}
