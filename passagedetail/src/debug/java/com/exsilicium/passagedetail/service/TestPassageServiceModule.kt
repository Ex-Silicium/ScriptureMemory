package com.exsilicium.passagedetail.service

import dagger.Binds
import dagger.Module

@Module
/**
 * This really belongs in the androidTest source set. AS doesn't generate a JAR from test source sets so this is the
 * only way I can find to share this class with other projects.
 */
internal abstract class TestPassageServiceModule {

    @Binds
    abstract fun bindPassageService(service: TestPassageService): PassageService
}