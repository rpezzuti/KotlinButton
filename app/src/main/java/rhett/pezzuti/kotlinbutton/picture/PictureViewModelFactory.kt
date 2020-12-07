package rhett.pezzuti.kotlinbutton.picture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rhett.pezzuti.kotlinbutton.database.PresetDatabase
import rhett.pezzuti.kotlinbutton.database.PresetDatabaseDao


class PictureViewModelFactory(
    private val presetKey: Long,
    private val database: PresetDatabaseDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PictureViewModel::class.java)) {
            return PictureViewModel(presetKey, database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}