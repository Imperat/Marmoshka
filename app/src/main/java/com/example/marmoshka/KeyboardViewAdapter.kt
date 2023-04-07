package com.example.marmoshka

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class KeyBoardViewAdapter(private val valuesChooser: ValuesChooser) : RecyclerView.Adapter<KeyBoardViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.keyboard_key, parent, false)

        return ViewHolder(
            view,
            ContextCompat.getDrawable(parent.context, R.drawable.border_button),
            ContextCompat.getDrawable(parent.context, R.drawable.border_used_button)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = valuesChooser.getItem(position);
        holder.button.text = item.toString()
        holder.setItem(item);
    }

    override fun getItemCount(): Int = valuesChooser.getItemCount()

    inner class ViewHolder(
        view: View,
        private val regularButton: Drawable?,
        private val usedButton: Drawable?
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val button: Button;
        private var item: ValueItem? = null;

        init {
            button = view.findViewById(R.id.button)
            button.setOnClickListener(this)
        }

        fun setItem(item: ValueItem) {
            button.text = item.number.toString();
            button.background = if (item.isUsed) usedButton else regularButton;

            this.item = item;
        }

        override fun onClick(p0: View?) {
            if (item == null) {
                return
            }

            valuesChooser.trigger(item!!)
        }
    }
}