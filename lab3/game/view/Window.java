package game.view;

import game.Message;
import game.model.Facade;
import game.observer.IObserver;
import game.theme.ThemeReader;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Window extends JFrame implements IObserver{
    private final Facade facade;
    private final RecordsHandler recordsHandler;
    private final int size;

    private Map<Integer, Integer> map;
    private Tile[][] tiles;

    JPanel grid;
    JPanel scores;
    GridLayout gridLayout;
    GridLayout recordLayout;
    LayoutManager mainLayout;

    Box recordBox;
    Box gridBox;
    JPanel content;

    public Window(Facade facade){
        facade.getObject().registerObserver(this);

        recordsHandler = new RecordsHandler();

        size = facade.getSize();
        this.facade = facade;

        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        initContent();

        initColorMap();

        setBest();

        redrawGrid(facade.getNums());
    }

    @Override
    public void update(Message msg) {
        switch (msg){
            case UPDATE -> {
                grid.setVisible(true);
                redrawGrid(facade.getNums());
                repaintScore(facade.getScore());
            }

            case GAME_OVER -> {
                System.out.println("Game Over!");
                gameOverScreen();
            }
            case REACH_2048 -> {
                System.out.println("Reaching the 2048!");
                winScreen();
            }
        }
    }

    private void initContent(){
        content = new JPanel();
        this.getContentPane().add(content);

        mainLayout = new BoxLayout(content, BoxLayout.Y_AXIS);
        recordBox = Box.createHorizontalBox();
        gridBox = Box.createHorizontalBox();

        Box.createGlue();

        content.add(recordBox);
        content.add(gridBox);

        initScores();
        initGrid();

        scores.setPreferredSize(new Dimension(520, 80));
        grid.setPreferredSize(new Dimension(520, 520));

        recordBox.add(scores);
        gridBox.add(grid);
    }

    JLabel labelScore;
    JLabel labelRecord;

    JPanel curScore;
    JPanel recordScore;

    private void initScores(){
        scores = new JPanel();
        recordLayout = new GridLayout(2, 2, 0, 0);
        scores.setLayout(recordLayout);

        scores.setVisible(true);

        JPanel curScoreLabel = new JPanel();
        curScoreLabel.add(generateStaticLabel("Score:"));

        JPanel recordLabel = new JPanel();
        recordLabel.add(generateStaticLabel("Best:"));

        curScore = new JPanel();
        recordScore = new JPanel();

        labelScore = generateScoreLabel();
        curScore.add(labelScore);

        labelRecord = generateScoreLabel();
        recordScore.add(labelRecord);

        scores.add(curScoreLabel);
        scores.add(recordLabel);
        scores.add(curScore);
        scores.add(recordScore);
    }

    void setBest(){
        if (facade.getScore() > recordsHandler.getRecord()) recordsHandler.writeNewRecord(facade.getScore());
        labelRecord.setText(Integer.toString(recordsHandler.getRecord()));
    }

    private JLabel generateScoreLabel(){
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 36));
        label.setText("0");
        label.setBorder(BorderFactory.createEmptyBorder(-4, 1, 1, 1));
        label.setVerticalAlignment(SwingConstants.CENTER);

        return label;
    }

    private JLabel generateStaticLabel(String text){
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        label.setText(text);
        label.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        label.setVerticalAlignment(SwingConstants.CENTER);

        return label;
    }

    private void initGrid(){
        tiles = new Tile[size][size];
        grid = new JPanel();
        gridLayout = new GridLayout(size,size, 5, 5);
        grid.setLayout(gridLayout);

        for (int i = 0; i != size; i++){
            for (int j = 0; j != size; j++) {
                tiles[j][i] = new Tile();
                grid.add(tiles[j][i]);
            }
        }
    }

    private void redrawGrid(int[][] arr){
        int newColor;
        int[] last = facade.getLastGenerated();

        for (int i = 0; i != size; i++){
            for (int j = 0; j != size; j++) {
                if (i == last[0] && j == last[1]) {
                    newColor = map.get(-1);
                }
                else if (arr[i][j] > 2048)
                    newColor = map.get(4096);

                else newColor = map.get(arr[i][j]);

                tiles[j][i].change(arr[i][j], newColor);
            }
        }

    }

    private void repaintScore(int score){
        setBest();
        labelScore.setText(Integer.toString(score));
        curScore.repaint();
    }

    private void initColorMap(){
        ThemeReader tr = new ThemeReader();
        map = tr.getColorMap();
    }

    private void gameOverScreen(){
        setBest();
        new GameOverWindow(facade);
    }

    private void winScreen(){

        new Reaching2048Window(facade);

    }
}
