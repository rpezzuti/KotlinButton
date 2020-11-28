package rhett.pezzuti.kotlinbutton.home

import android.app.Application
import android.widget.Button
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rhett.pezzuti.kotlinbutton.database.ButtonPreset
import rhett.pezzuti.kotlinbutton.database.PresetDatabaseDao
import timber.log.Timber

private const val TAG = "HomeViewModel"

class HomeViewModel(
    val database: PresetDatabaseDao,
    application: Application
) : AndroidViewModel(application){

    /** Encapsulated Variables **/
    private val _number = MutableLiveData<Int>()
    val number : LiveData<Int>
        get() = _number

    private val _eventChangeText = MutableLiveData<Boolean>()
    val eventChangeText : LiveData<Boolean>
        get() = _eventChangeText

    private val _eventLaunch = MutableLiveData<Boolean>()
    val eventLaunch : LiveData<Boolean>
        get() = _eventLaunch

    private val _showSnackBarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent : LiveData<Boolean>
        get() = _showSnackBarEvent


    /** Current Mutable Preset, could be null **/
    private var preset = MutableLiveData<ButtonPreset?>()

    private var homeViewModelJob = Job()
    override fun onCleared() {
        Timber.i("onCleared() called")
        homeViewModelJob.cancel()
        super.onCleared()
    }

    /** Companion Object **/
    companion object {
        private const val TAG = "HomeViewModel"
    }

    /** Init Block **/
    init {
        Timber.i("Init block started")
        initializePreset()
        Timber.i("initializePreset() finished")
        _number.value = 0
        _eventChangeText.value = false
        _eventLaunch.value = false
        _showSnackBarEvent.value = false
    }

    private fun initializePreset() {
        viewModelScope.launch {
            Timber.i("initializePreset() called")
            preset.value = getCurrentPresetFromDatabase()
        }
    }

    private suspend fun getCurrentPresetFromDatabase(): ButtonPreset?{
        return withContext(Dispatchers.IO) {
            Timber.i("getCurrentPresetFromDatabase() called")
            var preset = database.getCurrentPreset()

            if (preset?.sound == -1){
                preset = null
            }
            preset
        }
    }


    /** Navigation Methods **/
    fun onSetText(){
        Timber.i("onSetText() called")
        _eventChangeText.value = true
        Timber.i("eventChangedText.value set to true")
    }
    fun doneChangingText(){
        _eventChangeText.value = false
    }
    fun onLaunch(){
        _eventLaunch.value = true
    }
    fun doneLaunching(){
        _eventLaunch.value = false
    }
    fun doneShowingSnackBar(){
        _showSnackBarEvent.value = false
    }

    fun onClear(){
        viewModelScope.launch {
            clear()
            preset.value = null
            _showSnackBarEvent.value = true
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO){
            database.clear()
        }
    }


}
