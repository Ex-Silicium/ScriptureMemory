package com.exsilicium.screennavigator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.bluelinelabs.conductor.Controller

interface ScreenNavigator {
    fun init(activity: AppCompatActivity, rootScreen: Controller)

    fun push(screenTransaction: ScreenTransaction)

    fun pop(): Boolean

    fun popTo(tag: String)

    fun popToRoot()

    fun popToHome()

    fun popWithResult(resultCode: Int, intent: Intent)

    fun getTopController(): Controller

    fun backStackSize(): Int

    fun clear()
}