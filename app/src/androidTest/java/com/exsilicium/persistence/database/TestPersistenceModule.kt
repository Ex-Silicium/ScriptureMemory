package com.exsilicium.persistence.database

import dagger.Binds
import dagger.Module

@Module
internal abstract class TestPersistenceModule {

    @Binds
    abstract fun provideMockPassageDao(testPassageDao: TestPassageDao): PassageDao
}