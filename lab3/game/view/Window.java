package game.view;

import game.Controls;
import game.Message;
import game.model.Facade;
import game.theme.ThemeReader;
import game.view.tiles.Tile;
import game.view.tiles.TileColors;
import game.view.tiles.TilePics;

import javax.swing.*;
import java.awt.*;
import java.lang.constant.Constable;
import java.util.Map;

public class Window extends JFrame{
    private final Facade facade;
    private final RecordsHandler recordsHandler;
    private final int size;

    private Map<Integer, String> map;
    private Tile[][] tiles;

    private int mode;

    JPanel grid;
    JPanel scores;
    GridLayout gridLayout;
    GridLayout recordLayout;
    LayoutManager mainLayout;

    Box recordBox;
    Box gridBox;
    JPanel content;

    /**
     * Creates swing window for the game
     * @param facade - facade of Game Core
     * @param mode - style of tiles: 0 - Colors (from 'theme/colors.txt'),
     *             1 - Pictures (from 'sprites/{0-4096}.png');
     * @param controller - key Listener and update messages sender
     */
    public Window(Facade facade, int mode, Controls controller){
        initDefaultWindow();

        recordsHandler = new RecordsHandler();

        size = facade.getSize();
        this.facade = facade;
        modeSetter(mode);
        this.addKeyListener(controller);

        initContent();

        setBest();

        if (mode == 0) {
            initColorMap();
            redrawGridColors(facade.getNums());
        }
        else
            redrawGridPics(facade.getNums());
    }

    private void modeSetter(int mode){
        if (mode == 0 || mode == 1) {
            this.mode = mode;
            return;
        }
        System.out.println("Unknown mode! Setting default mode (0)...");
        this.mode = 0;
    }

    private void initDefaultWindow(){
        this.setSize(545, 650);
        this.setVisible(true);
        this.setName("2048");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("src/game/sprites/logo.png");
        this.setIconImage(icon.getImage());
    }

    public void update(Message msg) {
        switch (msg){
            case UPDATE -> {
                if (mode == 0)
                    redrawGridColors(facade.getNums());
                else
                    redrawGridPics(facade.getNums());
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

        tiles = (mode == 0 ? new TileColors[size][size] : new TilePics[size][size]);

        grid = new JPanel();
        gridLayout = new GridLayout(size,size, 5, 5);
        grid.setLayout(gridLayout);

        for (int i = 0; i != size; i++){
            for (int j = 0; j != size; j++) {
                tiles[j][i] = (mode == 0 ? new TileColors() : new TilePics());
                grid.add((Component) tiles[j][i]);
            }
        }
    }

    private void redrawGridColors(int[][] arr){
        String newColor;
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

    private void redrawGridPics(int[][] arr){

        for (int i = 0; i != size; i++){
            for (int j = 0; j != size; j++) {
                tiles[j][i].change(arr[i][j], "");
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
