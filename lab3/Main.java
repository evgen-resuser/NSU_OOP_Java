
import game2048.Window;

import javax.swing.*;
import java.util.Arrays;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        Window win = new Window();

        win.setVisible(true);
        win.setTitle("2048");
        win.setSize(520,520);
        win.setDefaultCloseOperation(EXIT_ON_CLOSE);


//        int[] arr = {2, 2, 2, 2};
////        int[][] ar = {
////                {0, 2, 2, 0},
////                {2, 2, 0, 4},
////                {0, 0, 8, 4},
////                {2, 0, 0, 2},
////        };
//
//        int[][] ar = {
//                        {2, 2, 2, 2},
//                        {2, 2, 2, 2},
//                        {2, 2, 2, 2},
//                        {2, 2, 2, 2},
//                };
//        printArr(ar);
//        lineSmasher l = new lineSmasher(ar);
//        //l.smash('R');
//
//         l.smash('R');
//        printArr(ar);
//        l.smash('L');
//        printArr(ar);
//         l.smash('U');
//        printArr(ar);
//        l.smash('U');
//        printArr(ar);
//
    }

//    public static void printArr(int[][] arr){
//        for (int i = 0; i != 4; i++){
//            System.out.println(Arrays.toString(arr[i]));
//        }
//        System.out.println("-------------------");
//    }

}
