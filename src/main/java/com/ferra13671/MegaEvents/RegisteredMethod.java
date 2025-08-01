package com.ferra13671.MegaEvents;

import java.lang.reflect.Method;

/**
 * @author Ferra13671
 * @LastUpdate 1.5
 */

public class RegisteredMethod {
    public final Object object;
    public final Method method;
    public final boolean ghostEvent;

    public RegisteredMethod(Object object, Method method, boolean ghostEvent) {
        this.object = object;
        this.method = method;
        this.ghostEvent = ghostEvent;
    }
}