package com.exsilicium.scripturememory.extensions

import android.support.annotation.ColorRes
import com.exsilicium.scripture.shared.model.Book
import com.exsilicium.scripturememory.R

val Book.color: Int
    @ColorRes get() = if (isOldTestament()) R.color.colorAccent else R.color.colorPrimary
