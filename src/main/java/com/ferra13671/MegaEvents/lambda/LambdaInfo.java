package com.ferra13671.MegaEvents.lambda;

import com.ferra13671.MegaEvents.event.Event;

import java.util.function.Consumer;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.7
 */

public class LambdaInfo<T extends Event<T>> {
    public final Class<T> clazz;
    public final Consumer<T> listener;

    public LambdaInfo(Class<T> clazz, Consumer<T> listener) {
        this.clazz = clazz;
        this.listener = listener;
    }
}
