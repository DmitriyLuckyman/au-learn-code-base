package ru.spbau.bandurin.task4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for test RandomEvent and TimeEvent.
 */
public class Main {

    /**
     * Create RandomEvent and TimeEvent instance.
     * Add 5 listeners for each event with code phrase {eventName} - {listener number}
     * Add 5 listeners with created time phrase.
     * Then in infinity loop test ready state of event and if ready state is true call fireEvent method.
     * @param args ignored
     * @throws InterruptedException if Thread.sleep throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        List<Event> eventList = new ArrayList<Event>();
        eventList.add(new RandomEvent());
        eventList.add(new TimeEvent());
        for (Event event : eventList) {
            for(int i = 0; i < 5; i++){
                final int n = i;
                event.addListener(new ActionListener() {
                    private final int number = n + 1;
                    public void performAction(final Event currentEvent) {
                        System.out.printf("%s - подписчик №%d%n", currentEvent.getClass().getSimpleName(), number);
                    }
                });
                event.addListener(new ActionListener() {
                    private final String timeFormat;
                    {
                        long createTimestamp = System.currentTimeMillis();
                        DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance();
                        timeFormat = dateFormat.format(createTimestamp);
                    }
                    public void performAction(Event currentEvent) {
                        System.out.printf("%s - %s%n", currentEvent.getClass().getSimpleName(), timeFormat);
                    }
                });
                Thread.sleep(2000);
            }
        }

        for(;;){
            for (Event event : eventList) {
                if(event.ready()){
                    event.fireEvent();
                }
            }
            Thread.sleep(10);
        }
    }
}
