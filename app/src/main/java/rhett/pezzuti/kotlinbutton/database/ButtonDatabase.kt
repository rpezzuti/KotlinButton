package rhett.pezzuti.kotlinbutton.database

import android.widget.Button
import androidx.room.Database
import androidx.room.Insert
import androidx.room.RoomDatabase

@Database(entities = [ButtonVariables::class], version = 1, exportSchema = false)
abstract class ButtonDatabase : RoomDatabase() {




}