package com.exsilicium.common.dagger

import javax.inject.Inject

class ActivityComponentLifecyclePlugin @Inject constructor(
) : ComponentLifecyclePlugin {

    override fun componentCreated(componentId: String, component: Any) {
    }

    override fun componentDestroyed(componentId: String, component: Any?) {
        (component as? ActivityComponent<*>)?.disposableManager()?.clear()
    }
}
