package calculator.commands;

import calculator.CalculatorException;
import calculator.CmdInterface;
import calculator.Context;

public class PrintCmd implements CmdInterface{
    @Override
    public void doCmd(Context context, String[] args) throws CalculatorException {
        if (context.getStack().size() == 0) throw new CalculatorException("Stack is empty!");

        System.out.println(context.getStack().peek());
    }
}
