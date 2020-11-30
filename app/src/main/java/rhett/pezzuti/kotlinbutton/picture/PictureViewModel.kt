package rhett.pezzuti.kotlinbutton.picture

import android.database.sqlite.SQLiteCantOpenDatabaseException
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    /** Init Block **/
    init {
        Timber.i("called")
        _eventNavigateForward.value = false
    }

    fun picture(picture: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                Timber.i("called")
                val preset = database.get(presetKey)  ?: return@withContext
                preset.picture = picture
                Timber.i("it is not null")
                database.update(preset)

            }
            _eventNavigateForward.value = true
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