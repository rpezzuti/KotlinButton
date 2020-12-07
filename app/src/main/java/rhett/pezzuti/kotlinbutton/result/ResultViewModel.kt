package rhett.pezzuti.kotlinbutton.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rhett.pezzuti.kotlinbutton.database.ButtonPreset
import rhett.pezzuti.kotlinbutton.database.PresetDatabaseDao

class ResultViewModel(
    private val presetKey: Long,
    val database: PresetDatabaseDao
) : ViewModel() {

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome : LiveData<Boolean>
        get() = _navigateToHome


    private val preset = MediatorLiveData<ButtonPreset>()
    fun getPreset() = preset


    init {
        _navigateToHome.value = false
        preset.addSource(database.getPresetWithId(presetKey), preset::setValue)
    }

    fun goHome() {
        _navigateToHome.value = true
    }

    fun doneNavigatingHome() {
        _navigateToHome.value = false
    }

}