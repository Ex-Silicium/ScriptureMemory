package com.exsilicium.scripturememory.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.exsilicium.scripturememory.R
import com.exsilicium.scripturememory.di.Injector
import com.exsilicium.scripturememory.di.ScreenInjector
import com.exsilicium.scripturememory.ui.ScreenNavigator
import java.util.UUID
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject lateinit var screenInjector: ScreenInjector
    @Inject lateinit var screenNavigator: ScreenNavigator

    lateinit var instanceId: String
        private set
    private lateinit var router: Router

    protected abstract val layoutRes: Int
    protected abstract val rootController: BaseController

    override fun onCreate(savedInstanceState: Bundle?) {
        instanceId = if (savedInstanceState == null) {
            UUID.randomUUID().toString()
        } else {
            savedInstanceState.getString(INSTANCE_ID_KEY)
        }
        Injector.inject(this)
        setContentView(layoutRes)
        val screenContainer = findViewById<ViewGroup>(R.id.screen_container)
                ?: throw NullPointerException("Activity must have a view with id: screen_container")
        router = Conductor.attachRouter(this, screenContainer, savedInstanceState)
        monitorBackStack()

        screenNavigator.initWithRouter(router, rootController)

        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(INSTANCE_ID_KEY, instanceId)
    }

    override fun onBackPressed() {
        if (!screenNavigator.pop()) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        screenNavigator.clear()
        if (isFinishing) {
            Injector.clearComponent(this)
        }
    }

    private fun monitorBackStack() {
        router.addChangeListener(ComponentClearingChangeListener())
    }

    companion object {
        private const val INSTANCE_ID_KEY = "instance_id"
    }
}