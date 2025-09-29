package com.ferra13671.MegaEvents.lambda;

import com.ferra13671.MegaEvents.event.Event;
import com.ferra13671.MegaEvents.eventbus.IEventBus;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.6
 */

public class LambdaListenersDispatcher {
    private final IEventBus eventBus;
    private final HashMap<Class<Event<?>>, Consumer<?>> listeners = new HashMap<>();

    public LambdaListenersDispatcher(IEventBus eventBus) {
        this.eventBus = eventBus;
    }

    public <T extends Event<T>> void register(Class<T> clazz, Consumer<T> listener) {
        eventBus.register(listener);
        listeners.put((Class<Event<?>>) clazz, listener);
    }

    public void unregisterAll() {
        listeners.values().forEach(eventBus::unregister);
        listeners.clear();
    }
}
