package calculator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Scanner;

public class Calculator {
    private final Context context;
    private final Factory factory;
    private Scanner scanner;

    public Calculator(String path){
        context = new Context();
        factory = new Factory();

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(path));
            scanner = new Scanner(reader);
        } catch (IOException e) {
            System.out.println("The file is not open!");
            System.exit(1);
        }
    }

    public Calculator(){
        context = new Context();
        factory = new Factory();

        System.out.println("Type \"quit\" to end the program... ");
        scanner = new Scanner(System.in);
    }

    public void start(){

        String input;
        while ((scanner.hasNextLine())){
            input = scanner.nextLine();
            if (Objects.equals(input, "quit")) break;

            try {
                interpretString(input);
            } catch ( NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void interpretString(String input) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException{

        String[] args = input.split(" ");
        try {
            CmdInterface cmd = factory.createCmd(args[0]);
            cmd.doCmd(context, args);
        } catch (NullPointerException | CalculatorException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
        System.out.format("Command \"%s\" not found!\n", args[0]);
        }
    }
}
