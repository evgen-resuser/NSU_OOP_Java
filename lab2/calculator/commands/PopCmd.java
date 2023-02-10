package calculator;

public class PopCmd implements CmdInterface{
    @Override
    public void doCmd(Context context, String[] args) throws CalculatorException {
        if (context.getStack().size() == 0) throw new CalculatorException("Stack is empty!");

        context.getStack().pop();
    }
}
