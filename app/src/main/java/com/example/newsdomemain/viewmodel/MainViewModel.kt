package com.example.newsdomemain.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsdomemain.data.model.Model
import com.example.newsdomemain.data.network.retrofitService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _dataState = mutableStateOf(DataState())
    val dataState: State<DataState> = _dataState


    init {
        _dataState.value = DataState(loading = true)
        fetchCategories("india")
    }

    fun fetchCategories(query: String) {
        viewModelScope.launch {
            try {
                val response = retrofitService.getList(query)
                _dataState.value = _dataState.value.copy(
                    loading = false,
                    error = null,
                    list = response
                )

            } catch (e: Exception) {
                Log.d("FetchCategoryError", "fetchCategories: ${e.message}")
                _dataState.value = _dataState.value.copy(
                    loading = false,
                    error = e.message,
                    list = null
                )
            }
        }
    }


    data class DataState(
        val loading: Boolean = false,
        val list: Model? = null,
        val error: String? = null
    )
}