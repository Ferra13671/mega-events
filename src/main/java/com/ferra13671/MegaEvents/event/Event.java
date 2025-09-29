package com.ferra13671.MegaEvents.event;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.6
 */

public abstract class Event<T extends Event<T>> {
    private EventDispatcher<T> eventDispatcher;
    private boolean cancelled = false;

    public Event() {}

    public void setEventDispatcher(EventDispatcher<T> eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    public EventDispatcher<T> getEventDispatcher() {
        return eventDispatcher;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void cancel() {
        this.cancelled = true;
    }
}
