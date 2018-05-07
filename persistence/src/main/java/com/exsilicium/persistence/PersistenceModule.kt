package com.exsilicium.persistence

import android.arch.persistence.room.Room
import android.content.Context
import com.exsilicium.persistence.database.PassageDao
import com.exsilicium.persistence.database.PassageDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    internal fun provideRoomDatabase(applicationContext: Context): PassageDatabase {
        return Room.databaseBuilder(
                applicationContext,
                PassageDatabase::class.java,
                PASSAGE_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    internal fun providePassageDao(database: PassageDatabase): PassageDao = database.passageDao()

    private companion object {
        const val PASSAGE_DATABASE_NAME = "memory_passages"
    }
}