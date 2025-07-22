package com.ferra13671.MegaEvents;

/**
 * @author Ferra13671
 * @LastUpdate 1.4
 */

public abstract class Event {

    public Event() {}

    private boolean cancelled;

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
