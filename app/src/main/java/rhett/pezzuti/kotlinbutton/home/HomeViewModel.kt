package rhett.pezzuti.kotlinbutton.home

import android.app.Application
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

    private val _navigateToSetText = MutableLiveData<Boolean>()
    val navigateToSetText : LiveData<Boolean>
        get() = _navigateToSetText

    private val _navigateToButton = MutableLiveData<Boolean>()
    val navigateToButton : LiveData<Boolean>
        get() = _navigateToButton

    private val _showSnackBarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent : LiveData<Boolean>
        get() = _showSnackBarEvent


    private var homeViewModelJob = Job()
    override fun onCleared() {
        Timber.i("onCleared() called")
        homeViewModelJob.cancel()
        super.onCleared()
    }

    val presets = database.getAllPresets()

    /** Companion Object **/
    companion object {
        private const val TAG = "HomeViewModel"
    }

    /** Init Block **/
    init {
        Timber.i("Init block started")
        Timber.i("initializePreset() finished")
        _navigateToSetText.value = false
        _navigateToButton.value = false
        _showSnackBarEvent.value = false
    }



    private suspend fun insert(preset: ButtonPreset){
        withContext(Dispatchers.IO){
            database.insert(preset)
        }
    }

    /** Navigation Methods **/
    fun onSetText(){
        _navigateToSetText.value = true
    }
    fun doneChangingText(){
        _navigateToSetText.value = false
    }
    fun onLaunch(){
        _navigateToButton.value = true
    }
    fun doneLaunching(){
        _navigateToButton.value = false
    }
    fun doneShowingSnackBar(){
        _showSnackBarEvent.value = false
    }

    fun onClear(){
        viewModelScope.launch {
            clear()
            _showSnackBarEvent.value = true
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO){
            database.clear()
        }
    }


}
