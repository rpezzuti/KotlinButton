package rhett.pezzuti.kotlinbutton.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rhett.pezzuti.kotlinbutton.database.ButtonPreset
import rhett.pezzuti.kotlinbutton.database.PresetDatabaseDao

class GameViewModel(
    private val presetKey: Long,
    val database: PresetDatabaseDao
) : ViewModel() {

    private val _eventPressButton = MutableLiveData<Boolean>()
    val eventPressButton : LiveData<Boolean>
        get() =  _eventPressButton

    private val preset = MediatorLiveData<ButtonPreset>()
    fun getPreset() = preset

    init {
        _eventPressButton.value = false
        preset.addSource(database.getPresetWithId(presetKey), preset::setValue)
    }

    fun onPressButton(){
        _eventPressButton.value = true
    }
    fun onDonePressButton(){
        _eventPressButton.value = false
    }

















}