package game.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static java.lang.System.exit;

class RecordsHandler {
    private int record_;

    private final File file;

    public RecordsHandler(){
        try {
            readRecord();
        } catch (IOException e){
            System.out.println(e.getMessage());
            exit(1);
        }

        file = new File("src/game/view/best");
        try {
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else System.out.println("File already exists! Rewriting...");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getRecord(){
        return record_;
    }

    public void writeNewRecord(int newRecord){
        this.record_ = newRecord;

        try {
            FileWriter writer = new FileWriter(file, false);

            writer.write(Integer.toString(newRecord));
            writer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void readRecord() throws IOException{
        InputStream is = RecordsHandler.class.getResourceAsStream("best");

        if (is == null){
            record_ = 0;
            return;
        }

        Scanner scan = new Scanner(is);
        record_ = Integer.parseInt(scan.nextLine());

        scan.close();
    }



}
