package com.exsilicium.scripturememory.picker

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.exsilicium.scripturememory.picker.book.BookListController

object PassagePickerHelper {
    fun addPassage(router: Router) {
        router.pushController(RouterTransaction.with(BookListController.addPassage()))
    }
}
