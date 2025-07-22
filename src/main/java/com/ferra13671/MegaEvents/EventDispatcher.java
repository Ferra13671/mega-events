package com.ferra13671.MegaEvents;

import com.ferra13671.MegaEvents.exeptions.CreateEventInstanceException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * @author Ferra13671
 * @LastUpdate 1.4.3
 */

public class EventDispatcher<T extends Event> {
    private final List<RegisteredMethod> registeredMap = new CopyOnWriteArrayList<>();
    private Consumer<List<Object>> invokeConsumer = (args) -> {};
    private final Class<T> eventClass;

    public EventDispatcher(Class<T> eventClass) {
        this.eventClass = eventClass;
    }

    public T createEvent(Object... args) {
        try {
            T event;
            try {
                event = eventClass.getDeclaredConstructor().newInstance(args);
            } catch (Exception e) {
                event = eventClass.newInstance();
            }
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
