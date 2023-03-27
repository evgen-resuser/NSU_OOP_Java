package game.observer;

import game.Message;

public interface IObserver {
    void update(Message msg);
}
