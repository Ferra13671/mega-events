package com.ferra13671.MegaEvents.Base;


import com.ferra13671.MegaEvents.EventModifiers.EventPhase;

/**
 * @author Ferra13671
 * @LastUpdate 1.0
 */

public abstract class Event {
    private final EventPhase eventPhase;

    public Event(EventPhase eventPhase) {
        this.eventPhase = eventPhase;
    }

    public Event() {
        this.eventPhase = EventPhase.PRE;
    }

    private boolean cancelled;

    public EventPhase getPhase() {
        return eventPhase;
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
