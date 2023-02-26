package game.theme;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.exit;

public class ThemeReader {
    private final Map<Integer, Integer> colorMap;

    public ThemeReader(){
        colorMap = new HashMap<>();

        try {
            readColors();
        } catch (IOException e){
            System.out.println(e.getMessage());
            exit(1);
        }
    }
    private void readColors() throws IOException {
        InputStream is = ThemeReader.class.getResourceAsStream("colors.txt");

        if (is == null){
            throw new IOException("File colors.txt not found!");
        }

        Scanner scan = new Scanner(is);

        String input;

        while (scan.hasNextLine()){
            input = scan.nextLine();
            String[] str = input.split(" ");
            colorMap.put(Integer.parseInt(str[0]), Integer.parseInt(str[1], 16));
        }

        scan.close();
    }

    public Map<Integer, Integer> getColorMap() {
        return colorMap;
    }
}
