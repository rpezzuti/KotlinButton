package rhett.pezzuti.kotlinbutton.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rhett.pezzuti.kotlinbutton.database.PresetDatabaseDao

class DetailsViewModelFactory(
    private val presetKey: Long,
    private val database: PresetDatabaseDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(presetKey, database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}