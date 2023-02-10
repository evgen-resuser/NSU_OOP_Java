package calculator;

public class PushCmd implements CmdInterface{
    @Override
    public void doCmd(Context context, String[] args) throws CalculatorException {
        if (args.length < 2) throw new CalculatorException("Wrong number of arguments!");

        if (!context.getVarMap().containsKey(args[1])) throw new
                CalculatorException("Variable with name \""+args[1]+"\" wasn't defined!");

        Float a = context.getVarMap().get(args[1]);
        context.getStack().push(a);
    }
}
