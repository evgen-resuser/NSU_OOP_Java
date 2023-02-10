package calculator;

public class PlusCmd implements CmdInterface {
    @Override
    public void doCmd(Context context, String[] args) throws CalculatorException {
        if (context.getStack().size() < 2) throw new CalculatorException("Not enough arguments in the stack!");
        Float a = context.getStack().pop();
        Float b = context.getStack().pop();

        context.getStack().push(a+b);
    }
}
