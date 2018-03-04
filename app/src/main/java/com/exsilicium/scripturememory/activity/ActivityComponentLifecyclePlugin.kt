package com.exsilicium.scripturememory.activity

import com.exsilicium.common.dagger.ActivityComponent
import com.exsilicium.daggerextension.plugin.ComponentLifecyclePlugin
import javax.inject.Inject

internal class ActivityComponentLifecyclePlugin @Inject constructor(
) : ComponentLifecyclePlugin {
    override fun componentCreated(componentId: String, component: Any) {
    }

    override fun componentDestroyed(componentId: String, component: Any?) {
        (component as? ActivityComponent<*>)?.disposableManager()?.clear()
    }
}