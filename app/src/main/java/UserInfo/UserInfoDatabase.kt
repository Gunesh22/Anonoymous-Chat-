package UserInfo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserInfoDataClass::class], version = 1)
abstract class UserInfoDatabase : RoomDatabase()  {
    abstract fun userInfoDAO(): UserInfoDAO
    companion object {
        @Volatile
        private var INSTANCE: UserInfoDatabase? = null

        fun getDatabase(context: Context): UserInfoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserInfoDatabase::class.java,
                    "user_info_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}



