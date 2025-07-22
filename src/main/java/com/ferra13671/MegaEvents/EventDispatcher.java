package com.ferra13671.MegaEvents;

import com.ferra13671.MegaEvents.exeptions.CreateEventInstanceException;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * @author Ferra13671
 * @LastUpdate 1.4.5
 */

public class EventDispatcher<T extends Event<T>> {
    private final List<RegisteredMethod> registeredMap = new CopyOnWriteArrayList<>();
    private Consumer<List<Object>> invokeConsumer = (args) -> {};
    private final Class<T> eventClass;

    public EventDispatcher(Class<T> eventClass) {
        this.eventClass = eventClass;
    }

    public T createEvent(Object... args) {
        try {
            T event = null;
            for (Constructor<?> constructor : eventClass.getConstructors()) {
                try {
                    event = (T) constructor.newInstance(args);
                } catch (Exception ignored) {}
            }
            if (event == null)
                throw new Exception("Failed create event instance: ".concat(eventClass.getName()));
            event.setEventDispatcher(this);
            return event;
        } catch (Exception e) {
            throw new CreateEventInstanceException(e);
        }
    }

    public List<RegisteredMethod> getRegisteredMap() {
        return registeredMap;
    }

    public Consumer<List<Object>> getInvokeConsumer() {
        return invokeConsumer;
    }

    public void setInvokeConsumer(Consumer<List<Object>> invokeConsumer) {
        this.invokeConsumer = invokeConsumer;
    }

    public Class<T> getEventClass() {
        return eventClass;
    }
}
