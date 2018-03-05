package com.exsilicium.common.dagger

interface ComponentLifecyclePlugin {
    fun componentCreated(componentId: String, component: Any)
    fun componentDestroyed(componentId: String, component: Any?)
}
