package rhett.pezzuti.kotlinbutton.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


import rhett.pezzuti.kotlinbutton.database.ButtonPreset
import rhett.pezzuti.kotlinbutton.databinding.ButtonPresetViewBinding

class PresetAdapter(val clickListener: ButtonPresetListener) : ListAdapter<ButtonPreset,PresetAdapter.ViewHolder>(ButtonPresetDiffCallBack()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ButtonPresetViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: ButtonPreset, clickListener: ButtonPresetListener){
            binding.preset = item
            binding.clickListener = clickListener
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
}

class ButtonPresetDiffCallBack : DiffUtil.ItemCallback<ButtonPreset>() {

    override fun areItemsTheSame(oldItem: ButtonPreset, newItem: ButtonPreset): Boolean {
        return oldItem.presetId == newItem.presetId
    }

    override fun areContentsTheSame(oldItem: ButtonPreset, newItem: ButtonPreset): Boolean {
        return oldItem == newItem
    }
}

/** Takes a preset, uses its id, and returns the unit **/
class ButtonPresetListener(val clickListener: (presetId: Long, presetSound: Int) -> Unit) {
    fun onClick(preset: ButtonPreset) = clickListener(preset.presetId, preset.sound)
}