package calculator.commands;

import calculator.CalculatorException;
import calculator.CmdInterface;
import calculator.Context;

//Extra command (not necessary)

public class ArifmCmd implements CmdInterface {
    @Override
    public void doCmd(Context context, String[] args) throws CalculatorException {
        if (args.length < 2) throw new CalculatorException("Not Enough Arguments!");

        int count = args.length - 1;
        float sum = 0.0F;

        for (int i = 1; i != args.length; i++){
            try {
                sum += Float.parseFloat(args[i]);
            } catch (NumberFormatException e) {
                count--;
                System.out.println("Not a number: " + args[i]);
            }
        }

        context.getStack().push(sum/count);
    }
}
