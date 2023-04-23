package com.example.marmoshka

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marmoshka.databinding.KeyboardKeyBinding

class KeyBoardViewAdapter(private val valuesChooser: ValuesChooser) : RecyclerView.Adapter<KeyBoardViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: KeyboardKeyBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.keyboard_key,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = valuesChooser.getItem(position);
        holder.binding.item = item;
        holder.binding.valuesChooser = valuesChooser
    }

    override fun getItemCount(): Int = valuesChooser.getItemCount()

    inner class ViewHolder(
        val binding: KeyboardKeyBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
    }
}