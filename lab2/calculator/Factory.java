package calculator;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Factory {
    private final Map<String, String> cmdMap;

    public Factory(){
        cmdMap = new HashMap<>();
        initMap();
    }

    private void initMap(){
        InputStream is = Factory.class.getResourceAsStream("config.txt");
        assert is != null;
        Scanner scan = new Scanner(is);

        String input;

        while (scan.hasNextLine()){
            input = scan.nextLine();
            String[] str = input.split(" ");
            cmdMap.put(str[0], str[1]);
        }

        scan.close();
    }

    public CmdInterface createCmd(String name) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException, ClassNotFoundException {

        Class<?> cmd = Class.forName("calculator.commands."+cmdMap.get(name));
        Constructor<?> con = cmd.getDeclaredConstructor();
        Object o = con.newInstance();

        return (CmdInterface)o;
    }
}
