package game.model;

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

    public void collide(){

    }
}
