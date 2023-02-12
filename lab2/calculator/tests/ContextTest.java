package calculator.tests;

import calculator.CalculatorException;
import calculator.CmdInterface;
import calculator.Context;
import calculator.Factory;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ContextTest {

    @org.junit.jupiter.api.Test
    void getVarMap() {
        Context cont = new Context();
        int expect = 0;
        int actual = cont.getVarMap().size();

        assertEquals(expect, actual);
    }

    @org.junit.jupiter.api.Test
    void getVarMap_ADD() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, CalculatorException {

        Context cont = new Context();
        Factory factory = new Factory();

        CmdInterface define = factory.createCmd("DEFINE");

        define.doCmd(cont, new String[]{"DEFINE", "A", "1.0"});
        assertEquals(1, cont.getVarMap().size());
    }

    @org.junit.jupiter.api.Test
    void getVarMap_DELETION() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, CalculatorException {
        Context cont = new Context();
        Factory factory = new Factory();

        CmdInterface pop = factory.createCmd("POP");
        CmdInterface define = factory.createCmd("DEFINE");
        CmdInterface push = factory.createCmd("PUSH");

        define.doCmd(cont, new String[]{"DEFINE", "A", "1.0"});
        push.doCmd(cont, new String[]{"DEFINE", "A"});
        pop.doCmd(cont, new String[]{"POP"});

        assertEquals(1, cont.getVarMap().size());
    }

    @org.junit.jupiter.api.Test
    void getStack_NULL() {
        Context cont = new Context();

        assertEquals(0, cont.getStack().size());
    }

    @org.junit.jupiter.api.Test
    void getStack_DEF_WITHOUT_PUSH() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, CalculatorException {

        Context cont = new Context();
        Factory factory = new Factory();

        CmdInterface define = factory.createCmd("DEFINE");

        define.doCmd(cont, new String[]{"DEFINE", "A", "1.0"});

        assertEquals(0, cont.getStack().size());
    }

    @org.junit.jupiter.api.Test
    void getStack_ADD_ONE() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, CalculatorException {

        Context cont = new Context();
        Factory factory = new Factory();

        CmdInterface define = factory.createCmd("DEFINE");
        CmdInterface push = factory.createCmd("PUSH");

        define.doCmd(cont, new String[]{"DEFINE", "A", "1.0"});
        push.doCmd(cont, new String[]{"DEFINE", "A"});

        assertEquals(1, cont.getStack().size());
    }

    @org.junit.jupiter.api.Test
    void getStack_ADD_MORE() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, CalculatorException {

        Context cont = new Context();
        Factory factory = new Factory();

        CmdInterface define = factory.createCmd("DEFINE");
        CmdInterface push = factory.createCmd("PUSH");

        for (int i = 0; i != 4; i++){
            define.doCmd(cont, new String[]{"DEFINE", "A"+(i), "1.0"});
            push.doCmd(cont, new String[]{"DEFINE", "A"+(i)});
        }

        assertEquals(4, cont.getStack().size());
    }
}