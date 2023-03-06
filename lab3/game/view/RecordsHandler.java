package game.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.Cleaner;
import java.util.Scanner;

import static java.lang.System.exit;

class RecordsHandler {
    private int recordScore;

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
        return recordScore;
    }

    public void writeNewRecord(int newRecord){
        this.recordScore = newRecord;

        try (FileWriter writer = new FileWriter(file, false)){
            writer.write(Integer.toString(newRecord));
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void readRecord() throws IOException{
        InputStream is = RecordsHandler.class.getResourceAsStream("best");

        if (is == null){
            recordScore = 0;
            return;
        }

        Scanner scan = new Scanner(is);
        recordScore = Integer.parseInt(scan.nextLine());

        Cleaner.Cleanable cleanable = scan::close;
        cleanable.clean();
    }



}
