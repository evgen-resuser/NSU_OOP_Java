package game.view;

import game.theme.ThemeReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static java.lang.System.exit;

class RecordsHandler {
    private int record;

    public RecordsHandler(){
        try {
            readRecord();
        } catch (IOException e){
            System.out.println(e.getMessage());
            exit(1);
        }
    }

    public int getRecord(){
        return record;
    }

    public void writeNewRecord(){
        File file = new File("record.txt");

        try (FileWriter writer = new FileWriter(file, false)) {

            writer.write(Integer.toString(record));
            writer.append('\n');
            writer.flush();

        } catch (IOException e){
            System.out.println("File not found!");
        }
    }

    private void readRecord() throws IOException{
        InputStream is = ThemeReader.class.getResourceAsStream("record.txt");

        if (is == null){
            throw new IOException("File 'record.txt' not found!");
        }

        Scanner scan = new Scanner(is);
        record = Integer.parseInt(scan.nextLine());

        scan.close();
    }



}
