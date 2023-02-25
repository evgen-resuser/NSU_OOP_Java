package game.observer;

import game.model.Message;

public interface IObserver {
    public void update(Message msg);
}
