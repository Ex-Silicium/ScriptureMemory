package com.exsilicium.common.base

import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.exsilicium.common.dagger.Injector

internal class ComponentClearingChangeListener : ControllerChangeHandler.ControllerChangeListener {
    override fun onChangeCompleted(
            to: Controller?,
            from: Controller?,
            isPush: Boolean,
            container: ViewGroup,
            handler: ControllerChangeHandler
    ) {
        if (!isPush && from != null) {
            Injector.clearComponent(from)
        }
    }

    override fun onChangeStarted(
            to: Controller?,
            from: Controller?,
            isPush: Boolean,
            container: ViewGroup,
            handler: ControllerChangeHandler
    ) {
    }
}