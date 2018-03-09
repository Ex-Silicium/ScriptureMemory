package com.exsilicium.common.extension

import android.support.annotation.ColorRes
import com.exsilicium.common.R
import com.exsilicium.scripture.shared.model.Book

val Book.color: Int
    @ColorRes get() = if (isOldTestament()) {
        R.color.colorAccent
    } else {
        R.color.colorPrimary
    }
