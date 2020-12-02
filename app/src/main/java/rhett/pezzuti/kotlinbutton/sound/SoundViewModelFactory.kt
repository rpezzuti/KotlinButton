package rhett.pezzuti.kotlinbutton.sound

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rhett.pezzuti.kotlinbutton.database.PresetDatabaseDao
import rhett.pezzuti.kotlinbutton.home.HomeViewModel

class SoundViewModelFactory(
    private val presetKey: Long,
    private val database: PresetDatabaseDao) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SoundViewModel::class.java)) {
            return SoundViewModel(presetKey, database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}