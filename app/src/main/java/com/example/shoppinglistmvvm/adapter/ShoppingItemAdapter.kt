package com.example.shoppinglistmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistmvvm.R
import com.example.shoppinglistmvvm.data.db.entities.ShoppingItem
import com.example.shoppinglistmvvm.viewModel.ShoppingViewModel

class ShoppingItemAdapter(var items: List<ShoppingItem>, private val viewModel: ShoppingViewModel): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = items[position]
        with(holder) {
            tvAmount.text = "${currentItem.amount}"
            tvName.text = currentItem.name
            ivDelete.setOnClickListener {
                viewModel.delete(currentItem)
            }
            ivPlus.setOnClickListener {
                currentItem.amount++
                viewModel.upsert(currentItem)
            }
            ivMinus.setOnClickListener {
                if (currentItem.amount > 0) {
                    currentItem.amount--
                    viewModel.upsert(currentItem)
                }
            }
        }

    }

    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        val ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)
        val ivPlus: ImageView = itemView.findViewById(R.id.ivPlus)
        val ivMinus: ImageView = itemView.findViewById(R.id.ivMinus)
    }
}