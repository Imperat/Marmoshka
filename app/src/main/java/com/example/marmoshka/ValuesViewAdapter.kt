package com.example.marmoshka

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ValuesViewAdapter(private val valuesFiller: ValuesFiller): RecyclerView.Adapter<ValuesViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ValuesViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.values_row, parent, false)

        return ViewHolder(
            view,
            ContextCompat.getDrawable(parent.context, R.drawable.value_button),
            ContextCompat.getDrawable(parent.context, R.drawable.value_button_filled),
        )
    }

    override fun onBindViewHolder(holder: ValuesViewAdapter.ViewHolder, position: Int) {
        val buttons = listOf<Button>(
            holder.button1,
            holder.button2,
            holder.button3,
            holder.button4,
            holder.button5,
            holder.button6,
        );

        val row = valuesFiller.getRow(position).getValues()

        for (i in 0..5) {
            if (row[i] == null) {
                buttons[i].text = "#";
                buttons[i].background = holder.regularButton;
            } else {
                buttons[i].text = row[i].toString();
                buttons[i].background = holder.usedButton;
            }
        }
    }

    override fun getItemCount(): Int {
        return 6; // TODO: FIX
    }

    inner class ViewHolder(
        view: View,
        val regularButton: Drawable?,
        val usedButton: Drawable?
    ): RecyclerView.ViewHolder(view) {
        val button1: Button;
        val button2: Button;
        val button3: Button;
        val button4: Button;
        val button5: Button;
        val button6: Button;

        init {
            button1 = view.findViewById(R.id.button1)
            button2 = view.findViewById(R.id.button2)
            button3 = view.findViewById(R.id.button3)
            button4 = view.findViewById(R.id.button4)
            button5 = view.findViewById(R.id.button5)
            button6 = view.findViewById(R.id.button6)
        }
    }
}