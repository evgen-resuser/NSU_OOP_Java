package calculator;

import static java.lang.Math.sqrt;

public class SqrtCmd implements CmdInterface {
    @Override
    public void doCmd(Context context, String[] args) throws CalculatorException {
        if (context.getStack().size() == 0) throw new CalculatorException("Stack is empty!");

        if (context.getStack().peek() < 0.0) throw new CalculatorException("The root expression is negative!");

        Float a = context.getStack().pop();

        context.getStack().push((float) sqrt(a));
    }
}
