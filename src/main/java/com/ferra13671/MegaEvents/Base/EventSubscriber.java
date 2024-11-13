package com.ferra13671.MegaEvents.Base;


import com.ferra13671.MegaEvents.EventModifiers.EventPriority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ferra13671
 * @LastUpdate 1.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventSubscriber {
    int priority() default EventPriority.DEFAULT;
}