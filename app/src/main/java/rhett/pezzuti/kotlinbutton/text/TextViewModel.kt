package rhett.pezzuti.kotlinbutton.text

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextViewModel : ViewModel() {

    private val _eventSaveMessage = MutableLiveData<Boolean>()
    val eventSaveMessage : LiveData<Boolean>
        get() = _eventSaveMessage

    init {
        _eventSaveMessage.value = false
    }

    fun onSaveMessage(){
        _eventSaveMessage.value = true
    }
    fun onDoneSaveMessage(){
        _eventSaveMessage.value = false
    }

}