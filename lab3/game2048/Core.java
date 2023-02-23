package game2048;

import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.processing.SupportedSourceVersion;

public class Core extends Thread{
    final int speed = 100;
    char direction = ' ';
    Controls controls;

    @Override
    public void run(){
        while (this.isAlive()) {
            direction = controls.getDirection();
            if (direction != ' ') doSmth();
        }
    }

    public Core(){
        controls = new Controls();
    }

    public Controls getControls() {
        return controls;
    }

    private void doSmth(){
        System.out.println("Key pressed: " + direction);
        controls.setDirection(' ');
    }
}
