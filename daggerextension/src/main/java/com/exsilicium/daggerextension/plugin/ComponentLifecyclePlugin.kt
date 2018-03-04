package com.exsilicium.daggerextension.plugin

interface ComponentLifecyclePlugin {
    fun componentCreated(componentId: String, component: Any)

    fun componentDestroyed(componentId: String, component: Any?)
}
