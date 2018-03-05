package com.exsilicium.common.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.exsilicium.common.R
import com.exsilicium.common.dagger.Injector
import com.exsilicium.common.dagger.ScreenInjector
import com.exsilicium.common.ui.ActivityLifecycleObserver
import com.exsilicium.screennavigator.ActivityScreen.Companion.EXTRA_BACK_ANIMATION
import com.exsilicium.screennavigator.ControllerScreen
import com.exsilicium.screennavigator.ScreenNavigator
import com.exsilicium.screennavigator.ScreenTransaction
import java.util.UUID
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject lateinit var screenInjector: ScreenInjector
    @Inject lateinit var screenNavigator: ScreenNavigator
    @Inject lateinit var lifecycleObservers: Set<@JvmSuppressWildcards ActivityLifecycleObserver>

    lateinit var instanceId: String
        private set
    lateinit var router: Router
        private set

    var backAnimation = -1
        private set

    open val isRoot: Boolean = false
    protected abstract val layoutRes: Int
    protected abstract val rootController: BaseController

    final override fun onCreate(savedInstanceState: Bundle?) {
        instanceId = if (savedInstanceState == null) {
            UUID.randomUUID().toString()
        } else {
            savedInstanceState.getString(INSTANCE_ID_KEY)
        }
        Injector.inject(this)
        super.onCreate(savedInstanceState)
        lifecycleObservers.forEach { it.register(this) }

        setContentView(layoutRes)
        val screenContainer = findViewById<ViewGroup>(R.id.screen_container)
                ?: throw NullPointerException("Activity must have a view with id: screen_container")
        router = Conductor.attachRouter(this, screenContainer, savedInstanceState)
        monitorBackStack()

        lifecycleObservers.forEach { it.routerAttached() }

        if (savedInstanceState == null) {
            screenNavigator.push(ScreenTransaction(ControllerScreen(rootController, isRoot = true)))
        }

        backAnimation = intent.getIntExtra(EXTRA_BACK_ANIMATION, -1)
        if (backAnimation == -1) {
            backAnimation = R.anim.slide_out_to_right
        }
    }

    final override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(INSTANCE_ID_KEY, instanceId)
    }

    final override fun onBackPressed() {
        if (!screenNavigator.pop()) {
            super.onBackPressed()
        }
    }

    final override fun onDestroy() {
        super.onDestroy()
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