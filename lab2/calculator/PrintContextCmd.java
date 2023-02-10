package calculator;

public class PrintContextCmd implements CmdInterface{
    @Override
    public void doCmd(Context context, String[] args) throws CalculatorException {
        System.out.print("Stack: ");
        System.out.println(context.getStack());
        System.out.print("Variable map: ");
        System.out.println(context.getVarMap());
    }
}
