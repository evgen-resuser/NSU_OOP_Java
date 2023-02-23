import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Stack;

public class lineSmasher {
    int[][] arrd;
    int len;

    public lineSmasher(int[][] arrr){
        arrd = arrr;
        len = arrd.length;
        //System.out.println(Arrays.toString(arr));
    }

    public void smash(char direction){
        int[] tmp;
        //for (int i = 0; i != 4; i++) {
            switch (direction) {
                case 'R' -> {
                    for (int i = 0; i != 4; i++) {
                        //ArrayUtils.reverse(arrd[i]);
                        collapse(arrd[i]);
                        ArrayUtils.reverse(arrd[i]);
                    }
                }
                case 'L' -> {
                    for (int i = 0; i != 4; i++) {
                        collapse(arrd[i]);
                    }
                }
                case 'D' -> {
                    for (int i = 0; i != 4; i++) {
                        tmp = generateCol(i);
                        collapse(tmp);
                        ArrayUtils.reverse(tmp);
                        insertNewCol(tmp, i);
                    }
                }
                case 'U' -> {
                    for (int i = 0; i != 4; i++) {
                        tmp = generateCol(i);
                        collapse(tmp);
                        insertNewCol(tmp, i);
                    }
                }

            }
        //}
    }

    public int[] generateCol(int col){
        int[] tmp = {0, 0, 0, 0};
        for (int i = 0; i != 4; i++){
            tmp [i] = arrd[i][col];
        }
        return tmp;
    }

    public void insertNewCol(int[] tmp, int col){
        for (int i = 0; i != 4; i++){
            arrd[i][col] = tmp [i];
        }
    }

    private void collapse(int[] arr){

        Stack<Integer> stack = new Stack<>();
        int temp = 0;

        for (int i = 0; i != len; i++){
            if (arr[i] == 0) continue;
            if (!stack.isEmpty()) temp = stack.peek();

            if (temp == arr[i]){
                stack.pop();
                stack.push(arr[i] + temp);
                arr[i] = 0;
                continue;
            }
            stack.push(arr[i]);
            arr[i] = 0;
        }

        for (int i = stack.size(); i != 0; i--) arr[i-1] = stack.pop();

    }
}
