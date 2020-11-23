package rhett.pezzuti.kotlinbutton.text

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class TextViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TextViewModel::class.java)) {
            return TextViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}