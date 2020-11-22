package rhett.pezzuti.kotlinbutton.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Update

import rhett.pezzuti.kotlinbutton.R
import rhett.pezzuti.kotlinbutton.database.ButtonPreset

class PresetAdapter : RecyclerView.Adapter<PresetAdapter.ViewHolder>() {

    /** this is the data to be displayed, and the setter below is used to tell the RV when data has changed. **/
    var data = listOf<ButtonPreset>()
        set(value) {
            field = value
            notifyDataSetChanged()
    }

    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val res = itemView.context.resources

        private val image: ImageView = itemView.findViewById(R.id.button_preset_image)
        private val message: TextView = itemView.findViewById(R.id.button_preset_text)
        private val sound: TextView = itemView.findViewById(R.id.button_preset_sound)

        fun bind(item: ButtonPreset){
            image.setImageResource(R.drawable.ic_launcher_background)
            message.text = R.string.app_name.toString()
            sound.text = R.string.app_name.toString()
            TODO("Update this shit")
        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                /** we're inflating a new view and returning it inside of a holder **/
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.button_preset_view, parent, false)
                return ViewHolder(view)
            }
        }


    }
}