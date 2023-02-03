public class Main {
    public static void main(String[] args) {;
        Counter count = new Counter(args[0]);
        count.doCount();

        Counter count2 = new Counter(args[0]);
        count2.doCount();
        System.out.println(count.equals(count2));
        System.out.println(count.hashCode());
    }
}