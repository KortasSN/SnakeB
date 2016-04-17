package com.Josh;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static sun.misc.PostVMInitHook.run;

/**
 * Created by Destro on 4/17/2016.
 */
public class SnakeGUI extends JFrame {
    private JPanel rootPanel;
    private JButton confirmSizeButton;
    private JCheckBox largeSizeCheckBox;
    private JCheckBox normalSizeCheckBox;
    private JCheckBox smallSizeCheckBox;
    private JLabel selectSizeLabel;
    private JCheckBox slowSpeedCheckBox;
    private JCheckBox normalSpeedCheckBox;
    private JCheckBox fastSpeedCheckBox;
    private JLabel selectSpeedLabel;
    private JButton confirmSpeedButton;
    private JButton startGameButton;

    private boolean wantsSlowSpeed;
    private boolean wantsNormalSpeed;
    private boolean wantsFastSpeed;
    private boolean wantsSmallSize;
    private boolean wantsNormalSize;
    private boolean wantsLargeSize;


    public static int xPixelMaxDimension; // = 501;  //Pixels in window. 501 to have 50-pixel squares plus 1 to draw a border on last square
    public static int yPixelMaxDimension; // = 501;
    public static int squareSize; // = 50;
    public static long clockInterval; // = 500;

    protected SnakeGUI() {
        setContentPane(rootPanel);
        pack();
        setVisible(true);


        confirmSizeButton.addActionListener(new ActionListener() {  //Select Speed confirm button
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        smallSizeCheckBox.addItemListener(new ItemListener() {  //Check box for SMALL size
            @Override
            public void itemStateChanged(ItemEvent e) {
                wantsSmallSize = smallSizeCheckBox.isSelected();
                if (wantsSmallSize == true) {
                    xPixelMaxDimension = 401;
                    yPixelMaxDimension = 401;
                    squareSize = (xPixelMaxDimension -1) / 10;
                }
            }
        });
        normalSizeCheckBox.addItemListener(new ItemListener() {  //Check box for NORMAL Size
            @Override
            public void itemStateChanged(ItemEvent e) {
                wantsNormalSize = normalSizeCheckBox.isSelected();
                if (wantsNormalSize == true) {
                    xPixelMaxDimension = 501;
                    yPixelMaxDimension = 501;
                    squareSize = (xPixelMaxDimension -1) / 10;
                }
            }
        });
        largeSizeCheckBox.addItemListener(new ItemListener() {  //Check box for LARGE size
            @Override
            public void itemStateChanged(ItemEvent e) {
                wantsLargeSize = largeSizeCheckBox.isSelected();
                if (wantsLargeSize == true) {
                    xPixelMaxDimension = 601;
                    yPixelMaxDimension = 601;
                    squareSize = (xPixelMaxDimension -1) / 10;
                }
            }
        });
        confirmSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        slowSpeedCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                wantsSlowSpeed = slowSpeedCheckBox.isSelected();
                if (wantsSlowSpeed == true) {
                    clockInterval = 400;
                }

            }
        });
        normalSpeedCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                wantsNormalSpeed = normalSpeedCheckBox.isSelected();
                if (wantsNormalSpeed == true) {
                    clockInterval = 500;
                }

            }
        });
        fastSpeedCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                wantsFastSpeed = fastSpeedCheckBox.isSelected();
                if (wantsFastSpeed == true) {
                    clockInterval = 600;
                }
            }
        });
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            run();
            }
        });
    }
}