package com.exsilicium.passagepicker

import com.exsilicium.common.R
import com.exsilicium.common.base.BaseActivity
import com.exsilicium.common.base.BaseController
import com.exsilicium.passagepicker.book.BookListController
import com.exsilicium.passagepicker.chapter.ChapterListController
import com.exsilicium.passagepicker.verse.VerseListController
import com.exsilicium.screennavigator.ActivityScreen
import com.exsilicium.screennavigator.ControllerScreen
import com.exsilicium.screennavigator.ScreenNavigator
import com.exsilicium.screennavigator.ScreenTransaction
import com.exsilicium.scripture.shared.model.Book

class PassagePickerActivity : BaseActivity() {
    // todo setup for tablet
    override val layoutRes = R.layout.activity_home
    override val rootController: BaseController = BookListController()

    companion object {
        fun addPassage(screenNavigator: ScreenNavigator) {
            // todo add intent processor / result code
            screenNavigator.push(ScreenTransaction(ActivityScreen(PassagePickerActivity::class)))
        }

        fun addPassageFromBook(screenNavigator: ScreenNavigator, book: Book) {
            screenNavigator.push(ScreenTransaction(ControllerScreen(ChapterListController.addPassage(book))))
        }

        fun addPassageFromChapter(screenNavigator: ScreenNavigator, book: Book, chapter: Int) {
            screenNavigator.push(ScreenTransaction(ControllerScreen(VerseListController.addPassage(book, chapter))))
        }
    }
}