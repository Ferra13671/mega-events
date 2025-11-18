package com.ferra13671.MegaEvents.event;

import com.ferra13671.MegaEvents.eventbus.IEventBus;

/**
 * The base class for all events.
 * It stores the {@link EventDispatcher} that called it.
 * The event can also be closed.
 *
 * @see IEventBus
 * @see EventDispatcher
 */

public abstract class Event<T extends Event<T>> {
    /** The event dispatcher that called this event. **/
    private EventDispatcher<T> eventDispatcher;
    /** Event closing status. **/
    private boolean cancelled = false;

    public Event() {}

    /**
     * Sets the event dispatcher for the event.
     *
     * @param eventDispatcher event dispatcher.
     */
    public void setEventDispatcher(EventDispatcher<T> eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    /**
     * Returns the event dispatcher that called this event.
     *
     * @return event dispatcher that called this event.
     */
    public EventDispatcher<T> getEventDispatcher() {
        return eventDispatcher;
    }

    /**
     * Returns whether the event is currently closed or not.
     *
     * @return whether the event is currently closed or not.
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets the event closing status.
     *
     * @param cancelled event closing status.
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Closes the event.
     */
    public void cancel() {
        this.cancelled = true;
    }
}
