package com.tz.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tz.shoppinglist.data.ShopListRepositoryImpl
import com.tz.shoppinglist.domain.DeleteShopItemUseCase
import com.tz.shoppinglist.domain.EditShopItemUseCase
import com.tz.shoppinglist.domain.GetShopListUseCase
import com.tz.shoppinglist.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val editShopItemUseCase = EditShopItemUseCase(repository)

    private val getShopListUseCase = GetShopListUseCase(repository)

    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun changeEnableState(shopItem: ShopItem) {
        val newShopItem = shopItem.copy(active = !shopItem.active)
        editShopItemUseCase.editShopItem(newShopItem)
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }
}