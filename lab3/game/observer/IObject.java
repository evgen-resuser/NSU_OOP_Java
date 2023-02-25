package game.observer;

public interface IObject {
    public void registerObserver(IObserver observer);
    public void notifyObserver();
}
