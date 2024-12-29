package csci.ooad.polymorphia;

public interface IObserver {
    void update(EventType event, String eventDescription);
}