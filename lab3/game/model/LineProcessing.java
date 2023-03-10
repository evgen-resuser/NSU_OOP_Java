package game.model;

import game.Message;
import game.observer.IObject;
import game.observer.IObserver;

import java.util.Stack;

public class LineProcessing implements IObject {

    //####################### OBSERVER PART #######################

    IObserver observer;

    @Override
    public void registerObserver(IObserver observer) {
        this.observer = observer;
    }

    @Override
    public void notifyObserver() {
        Message msg;
        msg = Message.REACH_2048;
        observer.update(msg);
    }

    //##############################################################

    private final int size;
    private int occCount;

    public int getOccCount() {
        return occCount;
    }

    public LineProcessing(int size){
        this.size = size;
        this.occCount = 0;

    }

    int collapseLeft(int[] arr, boolean[] tmpB){
        int temp = 0;
        int score = 0;
        boolean wasAdded = false;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i != size; i++){
            if (arr[i] == 0) continue;

            if (!stack.isEmpty()) temp = stack.peek();

            if (temp == arr[i] && !wasAdded){
                stack.pop();
                stack.push(arr[i] + temp);
                score += arr[i] + temp;
                if (arr[i] + temp == 2048) notifyObserver();
                arr[i] = 0;
                wasAdded = true;

            } else {
                wasAdded = false;
                stack.push(arr[i]);
                arr[i] = 0;
            }
        }

        occCount = stack.size();
        int shift = stack.size()-1;
        for (int i = shift; i != -1 ; i--) {
            arr[i] = stack.pop();
            tmpB[i] = true;
        }
        return score;
    }

    int collapseRight(int[] arr, boolean[] tmpB){
        int temp = 0;
        int score = 0;
        boolean wasAdded = false;
        Stack<Integer> stack = new Stack<>();

        for (int i = size-1; i != -1; i--){
            if (arr[i] == 0) continue;

            if (!stack.isEmpty()) temp = stack.peek();

            if (temp == arr[i] && !wasAdded){
                stack.pop();
                stack.push(arr[i] + temp);
                score += arr[i] + temp;
                if (arr[i] + temp == 2048) notifyObserver();
                arr[i] = 0;
                wasAdded = true;
            } else {
                wasAdded = false;
                stack.push(arr[i]);
                arr[i] = 0;
            }
        }

        occCount = stack.size();
        int shift = stack.size();
        for (int i = size-shift; i != size; i++) {
            arr[i] = stack.pop();
            tmpB[i] = true;
        }
        return score;
    }
}
