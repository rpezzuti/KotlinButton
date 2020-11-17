package rhett.pezzuti.kotlinbutton.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "button_preset_table")
data class ButtonPreset(

        @PrimaryKey(autoGenerate = true)
        var presetId: Long = 0L,

        @ColumnInfo(name = "message_text")
        var text: String = "text",

        @ColumnInfo(name = "message_sound")
        var sound: Int = 4,

        @ColumnInfo(name = "message_picture")
        var picture: Int = 5
)