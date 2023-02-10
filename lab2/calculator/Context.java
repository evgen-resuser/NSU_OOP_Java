package calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    private Map<String, Float> varMap;
    private Stack<Float> stack;

    public Context(){
        varMap = new HashMap<>();
        stack = new Stack<>();
    }

    public Map<String, Float> getVarMap() {
        return varMap;
    }

    public Stack<Float> getStack() {
        return stack;
    }
}
