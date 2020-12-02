package rhett.pezzuti.kotlinbutton.sound

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rhett.pezzuti.kotlinbutton.database.ButtonPreset
import rhett.pezzuti.kotlinbutton.database.PresetDatabaseDao
import timber.log.Timber

class SoundViewModel(
    private val presetKey: Long = 0L,
    val database: PresetDatabaseDao
) : ViewModel(){

    private val _eventGoHome = MutableLiveData<Boolean>()
    val eventGoHome : LiveData<Boolean>
        get() = _eventGoHome

    private val _sound = MutableLiveData<Int>()
    val sound : LiveData<Int>
        get() = _sound

    private var currentPreset = MutableLiveData<ButtonPreset>()

    init {
        _eventGoHome.value = false
    }

    fun selectSound(soundId: Int){
        viewModelScope.launch {
            Timber.i( "picture(quality: Int) called")
            withContext(Dispatchers.IO) {
                // not running because this shit is null
                val preset = database.get(presetKey)  ?: return@withContext
                Timber.i( "preset retrieved")
                preset.sound = soundId
                update(preset)
            }
            _eventGoHome.value = true
        }
    }

    private suspend fun update(preset: ButtonPreset){
        withContext(Dispatchers.IO){
            database.update(preset)
        }
    }
    fun doneGoingHome(){
        _eventGoHome.value = false
    }

    fun playSound(choice: Int){
        _sound.value = choice
    }

}