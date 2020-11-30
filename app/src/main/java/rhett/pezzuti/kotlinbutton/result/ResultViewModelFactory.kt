package rhett.pezzuti.kotlinbutton.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}