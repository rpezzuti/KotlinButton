package rhett.pezzuti.kotlinbutton.text

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rhett.pezzuti.kotlinbutton.database.ButtonPreset
import rhett.pezzuti.kotlinbutton.database.PresetDatabaseDao
import kotlinx.android.synthetic.main.*
import timber.log.Timber

class TextViewModel(
    val database: PresetDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    /** Encapsulated Variables **/
    private val _eventSaveMessage = MutableLiveData<Boolean>()
    val eventSaveMessage : LiveData<Boolean>
        get() = _eventSaveMessage

    private val _navigateToSetPicture = MutableLiveData<ButtonPreset>()
    val navigateToSetPicture : LiveData<ButtonPreset>
        get() = _navigateToSetPicture

    private var textViewModelJob = Job()
    override fun onCleared() {
        Timber.i("onCleared() called")
        textViewModelJob.cancel()
        super.onCleared()
    }

    /** Current Mutable Preset, could be null **/
    private var preset = MutableLiveData<ButtonPreset?>()

    init {
        Timber.i("init block called")
        initializePreset()
        _eventSaveMessage.value = false
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

    fun onSetPicture(message: String){
        viewModelScope.launch {
            Timber.i ("onSaveMessage() called")
            // When setting, make a new preset, set the text to the new text, and throw it in the database.
            // Then, set the mutableLiveData preset to the new one, with the new text string, causing the observer to trigger
            // Use that trigger to navigate to the picture fragment, with the presetID passed from the mutabledata preset.
            val newPreset = ButtonPreset()
            newPreset.text = message
            insert(newPreset)
            _navigateToSetPicture.value = newPreset
        }
    }

    private suspend fun insert(preset: ButtonPreset){
        withContext(Dispatchers.IO){
            Timber.i("called")
            database.insert(preset)
        }
    }

    fun onDoneSaveMessage(){
        Timber.i("onDoneSaveMessage() called")
        _eventSaveMessage.value = false
    }

    fun onMakePreset(){
        viewModelScope.launch {
            val preset = ButtonPreset()
            insert(preset)
        }
    }

    fun doneNavigating(){
        Timber.i("doneNavigating() called")
        _navigateToSetPicture.value = null
    }

}