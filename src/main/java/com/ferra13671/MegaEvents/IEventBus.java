package com.ferra13671.MegaEvents;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.5
 */

public interface IEventBus {

    void register(Object listener);

    void unregister(Object listener);

    <T extends Event<?>> void activate(T event);
}
