package rhett.pezzuti.kotlinbutton.details

import androidx.lifecycle.*
import rhett.pezzuti.kotlinbutton.database.ButtonPreset
import rhett.pezzuti.kotlinbutton.database.PresetDatabaseDao

class DetailsViewModel(
    val presetKey: Long,
    val database: PresetDatabaseDao
) : ViewModel() {

    private val _navigateToButtonFragment = MutableLiveData<Boolean>()
    val navigateToButtonFragment : LiveData<Boolean>
        get() = _navigateToButtonFragment


    private val preset = MediatorLiveData<ButtonPreset>()
    fun getPreset() = preset

    init {
        _navigateToButtonFragment.value = false
        preset.addSource(database.getPresetWithId(presetKey), preset::setValue)
    }


    fun navigateToButton() {
        _navigateToButtonFragment.value = true
    }

    fun doneNavigatingToButton() {
        _navigateToButtonFragment.value = false
    }


}
