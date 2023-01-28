import java.io.*;
import java.util.*;

public class Counter {
    private InputStreamReader reader;
    private Map<String, Integer> wordMap;
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
            wordMap = sortByValue(wordMap);
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

    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
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
