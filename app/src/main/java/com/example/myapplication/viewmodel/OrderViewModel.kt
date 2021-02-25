package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.repository.OrderRepository
import kotlinx.coroutines.flow.flow

class OrderViewModel(private val repository: OrderRepository) : ViewModel()  {
    fun order(page:Int) = flow {
        emit(repository.order(page))
    }
}