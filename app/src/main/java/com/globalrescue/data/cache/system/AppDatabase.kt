package com.globalrescue.data.cache.system

import androidx.room.Database
import androidx.room.RoomDatabase
import com.globalrescue.data.cache.dao.PostDao
import com.globalrescue.data.cache.entity.PostEntity

private const val DATABASE_VERSION = 1

@Database(
    entities = [PostEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun PostDao(): PostDao
}