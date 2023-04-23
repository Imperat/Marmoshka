package com.example.marmoshka

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marmoshka.databinding.ValuesRowBinding

class ValuesViewAdapter(private val valuesFiller: ValuesFiller): RecyclerView.Adapter<ValuesViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ValuesViewAdapter.ViewHolder {
        val binding : ValuesRowBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.values_row, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ValuesViewAdapter.ViewHolder, position: Int) {
        val row = valuesFiller.getRow(position).getValues()

        holder.binding.val1 = row[0];
        holder.binding.val2 = row[1];
        holder.binding.val3 = row[2];
        holder.binding.val4 = row[3];
        holder.binding.val5 = row[4];
        holder.binding.val6 = row[5];
    }

    override fun getItemCount(): Int {
        return 6; // TODO: FIX
    }

    inner class ViewHolder(
        val binding: ValuesRowBinding,
    ): RecyclerView.ViewHolder(binding.root) {
    }
}