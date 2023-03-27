package game.theme;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.exit;

public class ThemeReader {
    private final Map<Integer, String> colorMap;

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
            throw new IOException("File 'colors.txt' not found!");
        }

        Scanner scan = new Scanner(is);
        scan.nextLine(); // to skip the header

        String input;

        while (scan.hasNextLine()){
            input = scan.nextLine();
            String[] str = input.split(" ");
            colorMap.put(Integer.parseInt(str[0]), str[1]);
        }

        scan.close();
    }

    public Map<Integer, String> getColorMap() {
        return colorMap;
    }
}
