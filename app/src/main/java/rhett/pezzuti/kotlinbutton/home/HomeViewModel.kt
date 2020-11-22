package rhett.pezzuti.kotlinbutton.home

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import rhett.pezzuti.kotlinbutton.database.ButtonDatabaseDao
import java.util.*

private const val TAG = "HomeViewModel"

class HomeViewModel : ViewModel() {

    /** Encapsulated Variables **/
    private val _number = MutableLiveData<Int>()
    val number : LiveData<Int>
        get() = _number

    private val _eventChangeText = MutableLiveData<Boolean>()
    val eventChangeText : LiveData<Boolean>
        get() = _eventChangeText


    /** Companion Object **/
    companion object {
        private const val TAG = "HomeViewModel"
    }


    /** Init Block **/
    init {
        Log.i(TAG, "Init block started")
        _number.value = 0
        _eventChangeText.value = false
    }



    /** Navigation Methods **/
    fun onChangeText(){
        _eventChangeText.value = true
    }


}
