package calculator.commands;

import calculator.CalculatorException;
import calculator.CmdInterface;
import calculator.Context;

public class PrintContextCmd implements CmdInterface{
    @Override
    public void doCmd(Context context, String[] args) throws CalculatorException {
        System.out.print("Stack: ");
        System.out.println(context.getStack());
        System.out.print("Variable map: ");
        System.out.println(context.getVarMap());
    }
}
