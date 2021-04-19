package com.example.shoppinglistmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistmvvm.adapter.ShoppingItemAdapter
import com.example.shoppinglistmvvm.data.db.ShoppingDatabase
import com.example.shoppinglistmvvm.data.db.entities.ShoppingItem
import com.example.shoppinglistmvvm.data.repositories.ShoppingRepository
import com.example.shoppinglistmvvm.viewModel.ShoppingViewModel
import com.example.shoppinglistmvvm.viewModel.ShoppingViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val shoppingRecycler: RecyclerView = findViewById(R.id.shoppingRecylcer)
        val fabButton: FloatingActionButton = findViewById(R.id.fab)

        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)
        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        shoppingRecycler.layoutManager = LinearLayoutManager(this)
        shoppingRecycler.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fabButton.setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}