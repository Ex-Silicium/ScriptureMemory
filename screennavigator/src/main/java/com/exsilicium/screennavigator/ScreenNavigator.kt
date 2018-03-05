package com.exsilicium.screennavigator

import android.content.Intent
import com.bluelinelabs.conductor.Controller

interface ScreenNavigator {
    fun push(screenTransaction: ScreenTransaction)

    fun pop(): Boolean

    fun popTo(tag: String)

    fun popToRoot()

    fun popToHome()

    fun popWithResult(resultCode: Int, intent: Intent)

    fun getTopController(): Controller

    fun backStackSize(): Int
}