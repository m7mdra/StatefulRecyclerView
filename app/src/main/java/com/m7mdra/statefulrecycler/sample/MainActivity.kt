package com.m7mdra.statefulrecycler.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.m7mdra.statefulrecycler.sample.databinding.CustomEmptyViewBinding
import com.m7mdra.statefulrecyclerview.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = findViewById<StatefulRecyclerView>(R.id.statefulView)
        view.recyclerView {
            val list = ('a'..'z').toList()
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = object : RecyclerView.Adapter<ViewHolder>() {
                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): ViewHolder {
                    return ViewHolder(
                        LayoutInflater.from(parent.context)
                            .inflate(android.R.layout.simple_list_item_1, parent, false)
                    )

                }

                override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                    holder.text1.text = list[position].toString()
                }

                override fun getItemCount(): Int {
                    return list.size
                }

            }
        }

        view.progressViewBuilder {
            layoutInflater.inflate(R.layout.custom_progress_view, null, true)
        }
        view.emptyViewBuilder {
            val binding = CustomEmptyViewBinding.inflate(layoutInflater)
            binding.button.setOnClickListener {
                Toast.makeText(this@MainActivity, "button clicked".uppercase(), Toast.LENGTH_SHORT).show()
            }
            binding.root

        }

        view.errorButton {
            setOnClickListener {
                Toast.makeText(this@MainActivity, "Retry button clicked", Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<MaterialButton>(R.id.progressButton).setOnClickListener {
            view.moveToState(State.Loading)
        }
        findViewById<MaterialButton>(R.id.errorButton).setOnClickListener {
            view.moveToState(State.Error)
        }
        findViewById<MaterialButton>(R.id.emptyButton).setOnClickListener {
            view.moveToState(State.Empty)
        }
        findViewById<MaterialButton>(R.id.dataButton).setOnClickListener {
            view.moveToState(State.Data)
        }
        findViewById<MaterialButton>(R.id.defaultButton).setOnClickListener {
            view.moveToState(State.Default)
        }

    }
}