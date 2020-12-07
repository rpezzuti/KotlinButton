package rhett.pezzuti.kotlinbutton.database


import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PresetDatabaseDao {

    @Insert
    fun insert(preset: ButtonPreset)

    @Update
    fun update(preset: ButtonPreset)

    @Query("SELECT * FROM button_preset_table WHERE presetId = :key")
    fun get(key: Long): ButtonPreset?

    @Query("DELETE FROM button_preset_table")
    fun clear()

    /** Return all presets **/
    @Query("SELECT * FROM button_preset_table ORDER BY presetId DESC")
    fun getAllPresets(): LiveData<List<ButtonPreset>>

    @Query ("SELECT * FROM button_preset_table ORDER BY presetID DESC LIMIT 1")
    fun getCurrentPreset(): ButtonPreset?

    @Query ("SELECT * from button_preset_table WHERE presetId = :key")
    fun getPresetWithId(key: Long): LiveData<ButtonPreset>
}