package com.ferra13671.MegaEvents.Base;

/**
 * @author Ferra13671
 * @version 1
 */

public interface IEventBus {

    void register(Object object);

    void unregister(Object object);

    <T extends Event> void activate(T event);
}
