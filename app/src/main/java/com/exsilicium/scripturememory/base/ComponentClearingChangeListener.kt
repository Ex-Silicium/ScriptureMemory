package com.exsilicium.scripturememory.base

import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.exsilicium.scripturememory.di.Injector

class ComponentClearingChangeListener : ControllerChangeHandler.ControllerChangeListener {
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