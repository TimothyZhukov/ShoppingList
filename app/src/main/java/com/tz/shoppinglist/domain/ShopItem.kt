package com.tz.shoppinglist.domain

data class ShopItem(
    val name: String,
    val quantity: Int,
    val active: Boolean,
    var id: Int = UNDEFINED_ID
) {

    companion object {
        const val UNDEFINED_ID = -1
    }
}
