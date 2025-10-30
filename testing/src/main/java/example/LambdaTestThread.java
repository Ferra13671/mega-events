package example;

public class LambdaTestThread extends Thread {

    @Override
    public void run() {
        while (!isInterrupted()) {
            Main.eventBus.activate(LambdaEvent.EVENT_DISPATCHER.createEvent());

            try {
                sleep(1000);
            } catch (Exception ignored) {}
        }
    }
}
