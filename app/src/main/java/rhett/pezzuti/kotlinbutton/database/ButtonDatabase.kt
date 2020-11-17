package rhett.pezzuti.kotlinbutton.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ButtonPreset::class], version = 1, exportSchema = false)
abstract class ButtonDatabase : RoomDatabase() {

    abstract val buttonDatabaseDao: ButtonDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: ButtonDatabase? = null

        fun getInstance(context: Context) : ButtonDatabase {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            ButtonDatabase::class.java,
                            "sleep_history_database"
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