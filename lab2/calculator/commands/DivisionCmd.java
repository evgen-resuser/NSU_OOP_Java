package calculator.commands;

import calculator.CalculatorException;
import calculator.CmdInterface;
import calculator.Context;

public class DivisionCmd implements CmdInterface {
    @Override
    public void doCmd(Context context, String[] args) throws CalculatorException {
        if (context.getStack().size() < 2) throw new CalculatorException("Not enough arguments in the stack!");
        Float a = context.getStack().pop();
        Float b = context.getStack().pop();

        if (b == 0.0) {
            context.getStack().push(b);
            context.getStack().push(a);
            throw new CalculatorException("Division by zero!");
        }

        context.getStack().push(a/b);
    }
}

