package rhett.pezzuti.kotlinbutton.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rhett.pezzuti.kotlinbutton.database.PresetDatabaseDao

class GameViewModelFactory(
    private val presetKey: Long,
    private val database: PresetDatabaseDao
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(presetKey, database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }



}