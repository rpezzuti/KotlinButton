package rhett.pezzuti.kotlinbutton.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rhett.pezzuti.kotlinbutton.database.PresetDatabaseDao

class ResultViewModelFactory(
    private val presetKey: Long,
    private val database: PresetDatabaseDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(presetKey, database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}