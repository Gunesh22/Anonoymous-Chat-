package UserInfo

import com.google.firebase.firestore.FirebaseFirestore

class UserInfoRepository (private val userInfoDAO: UserInfoDAO) {

private val firestore = FirebaseFirestore.getInstance()

    suspend fun insert(userInfo: UserInfoDataClass): Long {
        firestore.collection("user_info").add(userInfo)
        return userInfoDAO.insert(userInfo)
    }

}