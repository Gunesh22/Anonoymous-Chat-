package UserInfo

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_info_table")
data class UserInfoDataClass(
    @PrimaryKey
    val uid : String,
    val firstName : String,
    val lastname : String,
    val phoneNumber : String

)
