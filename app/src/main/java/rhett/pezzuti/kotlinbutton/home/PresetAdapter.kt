package rhett.pezzuti.kotlinbutton.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


import rhett.pezzuti.kotlinbutton.database.ButtonPreset
import rhett.pezzuti.kotlinbutton.databinding.ButtonPresetViewBinding

class PresetAdapter : ListAdapter<ButtonPreset,PresetAdapter.ViewHolder>(SleepNightDiffCallBack()) {

   /* *//** this is the data to be displayed, and the setter below is used to tell the RV when data has changed. **//*
    var data = listOf<ButtonPreset>()
        set(value) {
            field = value
            notifyDataSetChanged()
    }*/


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ButtonPresetViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: ButtonPreset){
            binding.preset = item
            binding.executePendingBindings()
        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                /** we're inflating a new view and returning it inside of a holder **/
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ButtonPresetViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


    class SleepNightDiffCallBack : DiffUtil.ItemCallback<ButtonPreset>() {

        override fun areItemsTheSame(oldItem: ButtonPreset, newItem: ButtonPreset): Boolean {
            return oldItem.presetId == newItem.presetId
        }

        override fun areContentsTheSame(oldItem: ButtonPreset, newItem: ButtonPreset): Boolean {
            return oldItem == newItem
        }
    }

}