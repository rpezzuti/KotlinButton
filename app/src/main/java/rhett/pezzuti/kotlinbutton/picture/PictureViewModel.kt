package rhett.pezzuti.kotlinbutton.picture

import android.database.sqlite.SQLiteCantOpenDatabaseException
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import rhett.pezzuti.kotlinbutton.R
import rhett.pezzuti.kotlinbutton.database.ButtonPreset
import rhett.pezzuti.kotlinbutton.database.PresetDatabaseDao
import timber.log.Timber

class PictureViewModel(
    private val presetKey: Long = 0L,
    val database: PresetDatabaseDao
) : ViewModel(){


    /** Encapsulated Variables **/
    private val _eventNavigateForward = MutableLiveData<Boolean>()
    val eventNagivateForward : LiveData<Boolean>
        get() = _eventNavigateForward

    private val _navigatePreset = MutableLiveData<ButtonPreset>()
    val navigatePreset : LiveData<ButtonPreset>
        get() = _navigatePreset

    fun doneNavigating(){
        _navigatePreset.value = null
        _eventNavigateForward.value = false
    }

    /** Current Preset **/
    private val currentPreset = MutableLiveData<ButtonPreset>()

    /** Init Block **/
    init {
        Timber.i("called")
        _eventNavigateForward.value = false
    }

    fun picture(quality: Int) {
        viewModelScope.launch {
            Timber.i( "picture(quality: Int) called")
            withContext(Dispatchers.IO) {
                // not running because this shit is null
                val preset = database.get(presetKey)  ?: return@withContext
                Timber.i( "preset retrieved")
                preset.picture = quality
                update(preset)
            }
            _eventNavigateForward.value = true
        }
    }

    private suspend fun update(preset: ButtonPreset){
        withContext(Dispatchers.IO){
            database.update(preset)
        }
    }


    /** Navigation Methods **/
    fun onNavigate(){
        _eventNavigateForward.value = true
    }
    fun onDoneNavigating(){
        _eventNavigateForward.value = false
    }


    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared() called")
    }
}