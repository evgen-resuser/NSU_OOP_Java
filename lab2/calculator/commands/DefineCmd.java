package calculator.commands;

import calculator.CalculatorException;
import calculator.CmdInterface;
import calculator.Context;

public class DefineCmd implements CmdInterface{
    @Override
    public void doCmd(Context context, String[] args) throws CalculatorException {
        if (args.length != 3) throw new CalculatorException("Wrong number of arguments!");

        float flt = 0;
        try {
            flt = Float.parseFloat(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Calculation error: \"" + args[2] + "\" is not a float! (This value was set as zero)");
        }

        context.getVarMap().put(args[1], flt);
    }
}
