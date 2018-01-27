package com.exsilicium.scripturememory.home

import com.bluelinelabs.conductor.Controller
import com.exsilicium.scripturememory.di.ControllerKey
import com.exsilicium.scripturememory.passagedetail.PassageDetailComponent
import com.exsilicium.scripturememory.passagedetail.PassageDetailController
import com.exsilicium.scripturememory.picker.book.BookListComponent
import com.exsilicium.scripturememory.picker.book.BookListController
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(
        subcomponents = [
            MemoryPassageComponent::class,
            BookListComponent::class,
            PassageDetailComponent::class
        ]
)
abstract class MainScreenBindingModule {
    @Binds
    @IntoMap
    @ControllerKey(MemoryPassageController::class)
    abstract fun bindMemoryPassageController(builder: MemoryPassageComponent.Builder): AndroidInjector.Factory<out Controller>

    @Binds
    @IntoMap
    @ControllerKey(BookListController::class)
    abstract fun bindBookListController(builder: BookListComponent.Builder): AndroidInjector.Factory<out Controller>

    @Binds
    @IntoMap
    @ControllerKey(PassageDetailController::class)
    abstract fun bindPassageDetailController(builder: PassageDetailComponent.Builder): AndroidInjector.Factory<out Controller>
}
