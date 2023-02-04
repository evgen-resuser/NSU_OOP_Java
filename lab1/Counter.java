import java.io.*;
import java.util.*;

public class Counter {
    private InputStreamReader reader_;
    private Map<String, Integer> wordMap_;
    private int wordsCounter_;

    public Counter(String path){
        try {
            this.reader_ = new InputStreamReader(new FileInputStream(path));
        } catch (IOException e) {
            System.out.println("The file is not open!");
            System.exit(1);
        }
//        } finally {
//            try {
//                reader_.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        wordMap_ = new HashMap<>();
    }

    public void doCount() {
        try {

            String tmp;
            while (!(tmp = wordPicker()).equals("")){
                wordProcessing(tmp);
            }
            System.out.println("Creating output...");
            wordMap_ = sortByValue(wordMap_);
            generateOutput();

        } catch (IOException e){
            System.out.println("An I/O error occurred");
            System.exit(2);
        }
    }

    private void wordProcessing(String word){
        word = word.toLowerCase();
        if (!wordMap_.containsKey(word)){
            wordMap_.put(word, 1);
        } else {
            Integer tmp = wordMap_.get(word);
            wordMap_.put(word, ++tmp);
        }
        ++wordsCounter_;
    }

    private String wordPicker() throws IOException{
        StringBuilder sb = new StringBuilder();
        int ch;

        while ((ch = reader_.read()) != -1){
            if (Character.isLetterOrDigit((char) ch) || (char) ch == '\'') break;
        }

        while ((Character.isLetterOrDigit((char) ch) || (char) ch == '\'') && (ch != -1)){
            sb.append((char) ch);
            ch = reader_.read();
        }

        return sb.toString();
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    private void generateOutput() throws IOException {
        File file = new File("C:\\Users\\tupae\\Desktop\\output.csv");
        if (file.createNewFile()){
            System.out.println("File is created!");
        } else System.out.println("File already exists! Rewriting...");

        FileWriter writer = new FileWriter(file, false);
        String head = "Word,Entry(amount),Entry(percentage)";
        writer.write(head);
        writer.append('\n');

        for (var entry : wordMap_.entrySet()) {
            writer.write(entry.getKey() + "," + entry.getValue() + ',' + ((float)entry.getValue()/ wordsCounter_));
            writer.append('\n');
        }
        writer.flush();
    }

    public boolean equals(Counter other) {
        return this.wordMap_.equals(other.wordMap_);
    }

    public int hashCode(){
        return this.wordMap_.hashCode();
    }
}
