package game.observer;

import game.Message;

public interface IObserver {
    public void update(Message msg);
}
