package calculator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Calculator {
    private final Context context;
    private final Factory factory;
    private Scanner scanner;

    private final static Logger logger = Logger.getLogger(Calculator.class.getName());
    FileHandler fh;

    public Calculator(String path){
        context = new Context();
        factory = new Factory();

        loggerInit();
        logger.info("Creating the Calculator instance from file...\n----------------");

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(path));
            scanner = new Scanner(reader);
        } catch (IOException e) {
            logger.warning("The file is missing!");
            System.out.println("The file is not open!");
            System.exit(1);
        }
    }

    public Calculator(){
        context = new Context();
        factory = new Factory();

        loggerInit();
        logger.info("Creating the Calculator instance. Getting input from the console...\n----------------");

        System.out.println("Type \"quit\" to end the program... ");
        scanner = new Scanner(System.in);
    }

    private void loggerInit(){
        try {
            fh = new FileHandler("src/calculator/logs.txt");
            logger.setUseParentHandlers(false);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e){
            System.out.println("Logger fail!");
        }
    }

    public void start(){

        String input;
        while ((scanner.hasNextLine())){
            input = scanner.nextLine();
            if (Objects.equals(input, "quit")) {
                logger.info("Command quit: exit the program...\n----------------");
                fh.close();
                scanner.close();
                break;
            }

            try {
                logger.info("Trying to interpret command \"" + input + "\"... \n----------------");
                interpretString(input);
            } catch ( NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                logger.warning("Unknown exception!\n----------------");
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
        logger.info("Calculation complete!\n");
        fh.close();

    }

    private void interpretString(String input) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException{

        String[] args = input.split(" ");
        logger.info("Creating a command with Factory...\n----------------");
        try {
            CmdInterface cmd = factory.createCmd(args[0]);
            cmd.doCmd(context, args);
            logger.info("Do command: \nVariable Map: "+ context.getVarMap() + "\nStack: " + context.getStack() + "\n----------------");
        } catch (NullPointerException | CalculatorException e) {
            logger.warning("Calculation Error!\n----------------");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.warning("Command does not exist! Check the file name or config.txt!\n----------------");
        System.out.format("Command \"%s\" not found!\n", args[0]);
        }
    }
}
