package example;

import com.ferra13671.MegaEvents.event.Event;
import com.ferra13671.MegaEvents.event.EventDispatcher;
import com.ferra13671.MegaEvents.event.EventDispatchers;

public class LambdaEvent extends Event<LambdaEvent> {
    public static final EventDispatcher<LambdaEvent> EVENT_DISPATCHER = EventDispatchers.register(new EventDispatcher<>(LambdaEvent.class));
}
