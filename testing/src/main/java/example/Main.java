package example;

import com.ferra13671.MegaEvents.eventbus.EventBus;
import com.ferra13671.MegaEvents.eventbus.IEventBus;
import com.ferra13671.MegaEvents.lambda.LambdaListenersDispatcher;

public class Main {

    public static final IEventBus eventBus = new EventBus();

    public static void main(String[] args) {
        new LambdaEvent();

        LambdaListenersDispatcher lambdaDispatcher = new LambdaListenersDispatcher(eventBus);
        lambdaDispatcher.register(LambdaEvent.class, event -> {
            System.out.println("Lambda listener test!");
        });

        new LambdaTestThread().start();
    }
}
