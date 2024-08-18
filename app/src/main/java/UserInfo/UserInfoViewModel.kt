package UserInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserInfoViewModel(private val repository: UserInfoRepository) : ViewModel() {

    suspend fun insertUserInfo(userInfo: UserInfoDataClass) {
       viewModelScope.launch {
           repository.insert(userInfo)
       }
    }


}