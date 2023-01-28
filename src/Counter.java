import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Counter {
    private InputStreamReader reader;
    private final Map<String, Integer> wordMap;
    private int wordsCounter;

    public Counter(String path){
        try {
            this.reader = new InputStreamReader(new FileInputStream(path));
        } catch (IOException e) {
            System.out.println("The file is not open!");
            System.exit(1);
        }
        wordMap = new HashMap<>();
    }

    public void doCount() {
        try {

            String tmp;
            while (!(tmp = wordDivider()).equals("")){
                wordProcessing(tmp);
            }
            System.out.println("Creating output...");
            generateOutput();

        } catch (IOException e){
            System.out.println("An I/O error occurred");
            System.exit(2);
        }
    }

    private void wordProcessing(String word){
        word = word.toLowerCase();
        if (!wordMap.containsKey(word)){
            wordMap.put(word, 1);
        } else {
            Integer tmp = wordMap.get(word);
            wordMap.put(word, ++tmp);
        }
        ++wordsCounter;
    }

    private String wordDivider() throws IOException{
        StringBuilder sb = new StringBuilder();
        int a;

        while ((a = reader.read()) != -1){
            if (Character.isLetterOrDigit((char)a) || (char)a == '\'') break;
        }

        while ((Character.isLetterOrDigit((char)a) || (char)a == '\'') && (a != -1)){
            sb.append((char)a);
            a = reader.read();
        }

        return sb.toString();
    }

    private void generateOutput() throws IOException {
        File file = new File("C:\\Users\\tupae\\Desktop\\output.csv");
        if (file.createNewFile()){
            System.out.println("File is created!");
        } else System.out.println("File already exists! Rewriting...");

        FileWriter writer = new FileWriter(file, false);
        String head = "Word,Entry(amount),Entry(% of all)";
        writer.write(head);
        writer.append('\n');

        for (var entry : wordMap.entrySet()) {
            writer.write(entry.getKey() + "," + entry.getValue() + ',' + ((float)entry.getValue()/wordsCounter));
            writer.append('\n');
        }
        writer.flush();
    }

}
