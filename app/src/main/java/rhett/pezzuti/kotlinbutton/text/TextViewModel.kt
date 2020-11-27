package rhett.pezzuti.kotlinbutton.text

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rhett.pezzuti.kotlinbutton.database.ButtonPreset
import rhett.pezzuti.kotlinbutton.database.PresetDatabaseDao
import timber.log.Timber

class TextViewModel(
    private val presetKey: Long = 0L,
    val database: PresetDatabaseDao
) : ViewModel() {

    private val _eventSaveMessage = MutableLiveData<Boolean>()
    val eventSaveMessage : LiveData<Boolean>
        get() = _eventSaveMessage

    private var textViewModelJob = Job()
    override fun onCleared() {
        Timber.i("onCleared() called")
        textViewModelJob.cancel()
        super.onCleared()
    }

    init {
        Timber.i("init block called")
        _eventSaveMessage.value = false
    }

    fun onSaveMessage(){
        Timber.i ("onSaveMessage() called")
        _eventSaveMessage.value = true
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

    private suspend fun insert(preset: ButtonPreset) {
        withContext(Dispatchers.IO) {
            database.insert(preset)
        }
    }

}