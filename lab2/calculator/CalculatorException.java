package calculator;

public class CalculatorException extends Exception {
    public CalculatorException (String string){
        super("Calculation error: "+string);
    }
}
