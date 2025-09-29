package com.ferra13671.MegaEvents.eventbus;

import com.ferra13671.MegaEvents.event.Event;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.6
 */

public interface IEventBus {

    void register(Object listener);

    void unregister(Object listener);

    <T extends Event<?>> void activate(T event);
}
