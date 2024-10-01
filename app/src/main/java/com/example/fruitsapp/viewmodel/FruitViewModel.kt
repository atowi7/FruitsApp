package com.example.fruitsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitsapp.data.product.ProductRepository
import com.example.fruitsapp.model.Fruit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FruitViewModel(private val productRepository: ProductRepository):ViewModel() {
    private val _fruits = MutableStateFlow<List<Fruit>>(emptyList())
    val fruits: StateFlow<List<Fruit>> get() = _fruits

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
               val productList = productRepository.getProducts()
                println(productList)
                _fruits.value =productList
            }catch (e:Exception){
                println(e.printStackTrace())
            }
        }
    }
}