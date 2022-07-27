package com.tz.shoppinglist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tz.shoppinglist.R
import com.tz.shoppinglist.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var count = 0
    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("ShopListAdapter", "onCreateViewHolder, count: ${++count}")
        val layout = when (viewType) {
            VIEW_TYPE_ACTIVE -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        val status = if (shopItem.active) {
            "Active"
        } else {
            "Done!"
        }
        holder.tvName.text = "${shopItem.name} $status"
        holder.tvCount.text = shopItem.quantity.toString()
        holder.itemView.setOnLongClickListener {
            true
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (shopList[position].active) {
            return VIEW_TYPE_ACTIVE
        } else {
            return VIEW_TYPE_DISABLED
        }
    }

    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    companion object {

        const val VIEW_TYPE_ACTIVE = 1
        const val VIEW_TYPE_DISABLED = 0

        const val MAX_POOL_SIZE = 15
    }
}