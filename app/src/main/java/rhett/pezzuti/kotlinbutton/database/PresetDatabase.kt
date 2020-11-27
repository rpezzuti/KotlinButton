package rhett.pezzuti.kotlinbutton.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ButtonPreset::class], version = 1, exportSchema = false)
abstract class PresetDatabase : RoomDatabase() {

    abstract val presetDatabaseDao: PresetDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: PresetDatabase? = null

        fun getInstance(context: Context) : PresetDatabase {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            PresetDatabase::class.java,
                            "preset_history_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }



}