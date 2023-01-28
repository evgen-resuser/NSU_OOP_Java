public class Main {
    public static void main(String[] args) {
        String path = "C:\\Users\\tupae\\Desktop\\testRus.txt";
        Counter count = new Counter(path);
        //Counter count = new Counter(args[0]);
        count.doCount();
    }
}