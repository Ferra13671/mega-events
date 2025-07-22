package com.ferra13671.MegaEvents;

/**
 * @author Ferra13671
 * @LastUpdate 1.1
 */

public interface IEventBus {

    void register(Object object);

    void unregister(Object object);

    <T extends Event<?>> void activate(T event);
}
