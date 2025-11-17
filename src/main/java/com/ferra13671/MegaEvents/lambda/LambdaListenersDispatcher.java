package com.ferra13671.MegaEvents.lambda;

import com.ferra13671.MegaEvents.event.Event;
import com.ferra13671.MegaEvents.eventbus.IEventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.7
 */

public class LambdaListenersDispatcher {
    private final IEventBus eventBus;
    private final List<LambdaListener<?>> listeners = new ArrayList<>();

    public LambdaListenersDispatcher(IEventBus eventBus) {
        this.eventBus = eventBus;
    }

    public <T extends Event<T>> void register(Class<T> clazz, Consumer<T> listener) {
        LambdaListener<T> lambdaListener = new LambdaListener<>(clazz, listener);
        this.eventBus.register(lambdaListener);
        this.listeners.add(lambdaListener);
    }

    public void unregisterAll() {
        this.listeners.forEach(this.eventBus::unregister);
        this.listeners.clear();
    }
}
