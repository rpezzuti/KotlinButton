package rhett.pezzuti.kotlinbutton.text

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rhett.pezzuti.kotlinbutton.database.PresetDatabaseDao

@Suppress("UNCHECKED_CAST")
class TextViewModelFactory(
    private val presetKey: Long = 0L,
    private val dataSource: PresetDatabaseDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TextViewModel::class.java)) {
            return TextViewModel(presetKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}