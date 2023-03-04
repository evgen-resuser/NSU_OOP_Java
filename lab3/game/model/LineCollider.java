package game.model;

import java.util.Stack;

class LineCollider {
    int[][] arr;
    int size;

    public LineCollider(int[][] nums){
        arr = nums;
        size = nums.length;
    }

    public void transpose(){
        int[][] res = new int[size][size];

        for (int i = 0; i != size; i++){
            for (int j = 0; j != size; j++)
                res[i][j] = arr[j][i];
        }
        arr = res;
    }

    public void reverse(){
        int[][] res = new int[size][size];

        for (int i = 0; i != size; i++){
            for (int j = 0; j != size; j++)
                res[i][j] = arr[i][size-j-1];
        }
        arr = res;
    }

    public int collide(){

        int temp;
        int score = 0;

        for (int k = 0; k != size; k++)
        {
            boolean isSum = false;
            temp = 0;
            Stack<Integer> stack = new Stack<>();

            for (int i = size-1; i != -1; i--){

                if (!stack.isEmpty()) temp = stack.peek();

                if (arr[k][i] == 0) continue;

                if (arr[k][i] == temp && !isSum){
                    stack.pop();
                    stack.push(arr[k][i] + temp);
                    score += stack.peek();
                    isSum = true;
                    arr[k][i] = 0;
                    continue;
                }
                isSum = false;
                stack.push(arr[k][i]);
                arr[k][i] = 0;
            }

            int shift = stack.size();
            for (int i = size-shift; !stack.isEmpty(); i++) arr[k][i] = stack.pop();
        }

        return score;
    }
}
