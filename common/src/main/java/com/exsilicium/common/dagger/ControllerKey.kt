package com.exsilicium.common.dagger

import com.bluelinelabs.conductor.Controller
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ControllerKey(val value: KClass<out Controller>)
