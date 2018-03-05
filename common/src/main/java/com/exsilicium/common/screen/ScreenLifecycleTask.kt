package com.exsilicium.common.screen

import android.view.View

abstract class ScreenLifecycleTask {
    open fun onDestroyView(view: View) {}

    /**
     * Controller is no longer the top controller. The Controller may still be in the back stack, however.
     */
    open fun onExitScope() {}

    /**
     * Controller is now the top Controller.
     */
    open fun onEnterScope(view: View) {}

    /**
     * The Controller is no longer visible or in the back stack. This should only be used to clear any Activity level
     * bindings.
     */
    open fun onDestroy() {}
}