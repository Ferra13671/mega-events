package com.ferra13671.MegaEvents;

import java.lang.reflect.Method;

/**
 * @author Ferra13671
 * @LastUpdate 1.4
 */

public class RegisteredMethod {
    public final Object object;
    public final Method method;

    public RegisteredMethod(Object object, Method method) {
        this.object = object;
        this.method = method;
    }
}