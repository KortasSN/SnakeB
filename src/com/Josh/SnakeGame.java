package com.Josh;

import java.util.Scanner;
import java.util.Timer;

import javax.swing.*;


public class SnakeGame {


//{
//SnakeGUI gui = new SnakeGUI();
//
//    public SnakeGUI getGui() {
//        return gui;
//    }
//calculations for changing size//
    // small  xPixelMaxDimension = 401;
    // small  yPixelMaxDimension = 401;
    // small  squareSize = (xPixelMaxDimension - 1) / 10;
    // medium = no change
    // large xPixelMaxDimension = 601;
    // large xPixelMaxDimension = 601;
    // large squareSize = (xPixelMaxDimension - 1) / 10;
//**************probably need to remove next two lines*************//
    public  static int xPixelMaxDimension = 501;  //Pixels in window. 501 to have 50-pixel squares plus 1 to draw a border on last square
    public  static int yPixelMaxDimension = 501;

    public static int xSquares ;
    public static int ySquares ;

    public  static int squareSize = 50;

    protected static Snake snake ;
    protected static Options options ;
    private static GameComponentManager componentManager;

    protected static Score score;

    static final int BEFORE_GAME = 1;
    static final int DURING_GAME = 2;
    static final int GAME_OVER = 3;
    static final int GAME_WON = 4;
    static final int GAME_OPTIONS = 5;
    //The numerical values of these variables are not important. The important thing is to use the constants
    //instead of the values so you are clear what you are setting. Easy to forget what number is Game over vs. game won
    //Using constant names instead makes it easier to keep it straight. Refer to these variables
    //using statements such as SnakeGame.GAME_OVER

    private static int gameStage = BEFORE_GAME;  //use this to figure out what should be happening.
    //Other classes like Snake and DrawSnakeGamePanel will query this, and change its value

    protected static long clockInterval = 500; //controls game speed
    //Every time the clock ticks, the snake moves
    //This is the time between clock ticks, in milliseconds
    //1000 milliseconds = 1 second.

    static JFrame snakeFrame;
    static DrawSnakeGamePanel snakePanel;
    //Framework for this class adapted from the Java Swing Tutorial, FrameDemo and Custom Painting Demo. You should find them useful too.
    //http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/FrameDemoProject/src/components/FrameDemo.java
    //http://docs.oracle.com/javase/tutorial/uiswing/painting/step2.html


    public static void main(String[] args) {

        SnakeGUI gui = new SnakeGUI(new SnakeGame());

    }


    public void startGame() {

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                initializeGame();
                createAndShowGUI();
            }
        });
    }


    private static void createAndShowGUI() {
        //Create and set up the window.
        snakeFrame = new JFrame();
        snakeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //sets the frame the game is played in
        snakeFrame.setSize(xPixelMaxDimension, yPixelMaxDimension);
        snakeFrame.setUndecorated(true); //hide title bar
        snakeFrame.setVisible(true);
        snakeFrame.setResizable(false);

        snakePanel = new DrawSnakeGamePanel(componentManager);

        snakePanel.setFocusable(true);
        snakePanel.requestFocusInWindow(); //required to give this component the focus so it can generate KeyEvents

        snakeFrame.add(snakePanel);

        //Add listeners to listen for key presses
        System.out.println("Add listeners");
        snakePanel.addKeyListener(new GameControls());
        snakePanel.addKeyListener(new SnakeControls(snake));

        setGameStage(BEFORE_GAME);

        snakeFrame.setVisible(true);
    }

    private static void initializeGame() {

        //g.drawString("What size game?  small, medium or large?");
        Scanner sizeScanner = new Scanner(System.in);

        //set up score, snake and first kibble
        xSquares = xPixelMaxDimension / squareSize;
        ySquares = yPixelMaxDimension / squareSize;
        //setting the dimensions of the snake
        componentManager = new GameComponentManager();
        snake = new Snake(xSquares, ySquares, squareSize);
        Kibble kibble = new Kibble(snake);

        componentManager.addSnake(snake);
        componentManager.addKibble(kibble);
        componentManager.addOptions(options);

        score = new Score();

        componentManager.addScore(score);

        gameStage = BEFORE_GAME;
    }

    protected static void newGame() {
        Timer timer = new Timer();
        GameClock clockTick = new GameClock(componentManager, snakePanel);
        componentManager.newGame();
        timer.scheduleAtFixedRate(clockTick, 0, clockInterval);
    }


    public static int getGameStage() {
        return gameStage;
    }

    public static void setGameStage(int gameStage) {
        SnakeGame.gameStage = gameStage;
    }
}
