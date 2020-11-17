package rhett.pezzuti.kotlinbutton.text

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextViewModel : ViewModel() {


    /** LiveData Variables **/

    val _newMessage = MutableLiveData<String>()
    val newMessage : LiveData<String>
        get() = _newMessage

    val _eventNavForward = MutableLiveData<Boolean>()
    val eventNavForward : LiveData<Boolean>
        get() = _eventNavForward


    init {
        _newMessage.value = "Hello World!"
        _eventNavForward.value = false
    }


    fun onSave(message: String){
        _newMessage.value = message
    }
}