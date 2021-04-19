package com.example.shoppinglistmvvm

import com.example.shoppinglistmvvm.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}