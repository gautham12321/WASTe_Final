package com.example.waste.data

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow


@Entity(tableName = "UserData")
data class UserData(

    @PrimaryKey(autoGenerate = true)
    val id:Int=0,

    @ColumnInfo(name = "ModelName") val modelname: String,
    @ColumnInfo(name = "Type") val Type:String,
    @ColumnInfo val Price:String,
    @ColumnInfo val Status:String




    )

@Dao
interface  UserDao{

    @Query("Select * from UserData")
    suspend fun getAll(): List<UserData>

    @Insert()
   suspend fun insertDevice(userData: UserData)
}
@Database(entities = [UserData::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object
    {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }

    }

}

