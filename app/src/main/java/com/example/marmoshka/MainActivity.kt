package com.example.marmoshka

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val COLUMNS_COUNT = 9

class KeyBoardViewAdapter : RecyclerView.Adapter<KeyBoardViewAdapter.ViewHolder>() {
    private val values = (1..45).toList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.keyboard_key, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.button.text = item.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: Button;

        init {
            button = view.findViewById(R.id.button)
        }
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewResults: RecyclerView;
    private lateinit var recyclerViewKeyboard: RecyclerView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        recyclerViewResults = findViewById(R.id.recyclerViewResults)
        recyclerViewKeyboard = findViewById(R.id.recyclerViewKeyboard)

        with (recyclerViewKeyboard) {
            layoutManager = GridLayoutManager(context, 9)
            adapter = KeyBoardViewAdapter()
        }
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {


        return super.onCreateView(parent, name, context, attrs)
    }
}