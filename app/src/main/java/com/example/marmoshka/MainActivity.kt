package com.example.marmoshka

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


const val COLUMNS_COUNT = 9


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewResults: RecyclerView;
    private lateinit var recyclerViewKeyboard: RecyclerView;
    private lateinit var valuesChooser: ValuesChooser;
    private lateinit var valuesFiller: ValuesFiller;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        recyclerViewResults = findViewById(R.id.recyclerViewResults)
        recyclerViewKeyboard = findViewById(R.id.recyclerViewKeyboard)

        valuesFiller = ValuesFiller()
        valuesChooser = ValuesChooser(valuesFiller)

        valuesChooser.onUpdateUI {
            updateKeyboardAdapter(it)
        }

        valuesFiller.onUpdateUI {
            updateValuesAdapter(it)
        }

        updateKeyboardUI()
        updateValuesUI()

        Toast.makeText(this, "Давай, Мармошка! Выиграем $1000000 прямо сегодня!", Toast.LENGTH_SHORT).show()
    }

    private fun updateKeyboardUI() {
        updateKeyboardLayoutManager()
        updateKeyboardAdapter(null)
    }

    private fun updateValuesUI() {
        updateValuesLayoutManager();
        updateValuesAdapter(null);
    }

    private fun updateValuesLayoutManager() {
        with (recyclerViewResults) {
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun updateValuesAdapter(position: Int?) {
        with (recyclerViewResults) {
            if (position != null) {
                adapter?.notifyItemChanged(position);
                return;
            }

            adapter = ValuesViewAdapter(valuesFiller);
        }
    }

    private fun updateKeyboardAdapter(position: Int?) {
        with (recyclerViewKeyboard) {
            if (position != null) {
                adapter?.notifyItemChanged(position)
                return
            }

            adapter = KeyBoardViewAdapter(valuesChooser)
        }
    }

    private fun updateKeyboardLayoutManager() {
        with (recyclerViewKeyboard) {
            layoutManager = GridLayoutManager(context, COLUMNS_COUNT)
        }
    }
}
