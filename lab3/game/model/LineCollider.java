package game.model;

class LineCollider {
    int[][] test;

    public LineCollider(int[][] nums){
        test = new int[][]{
                {0, 2, 4, 8},
                {16, 32, 64, 128},
                {256, 512, 1024, 2048,},
                {4096, 0, 0, 0}
        };
    }

    public int[][] getTest() {
        return test;
    }
}
