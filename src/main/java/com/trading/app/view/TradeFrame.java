package com.trading.app.view;

import com.trading.app.model.TradeModel;
import com.trading.app.view.panels.TradePanel;

import javax.swing.*;
import java.awt.*;

public class TradeFrame extends JFrame {

    public static final Dimension WINDOW_SIZE = new Dimension(800, 800);

    private static final String WINDOW_TITLE = "Trade App";

    private final TradeModel tradeModel;

    private TradePanel tradePanel;


    public TradeFrame(TradeModel tradeModel) {
        this.tradeModel = tradeModel;
        tradePanel = new TradePanel();
        initSwingUI();
    }

    private void initSwingUI() {
        setTitle(WINDOW_TITLE);
        setSize(WINDOW_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(tradePanel);
        JMenuBar menuBar = new JMenuBar();
        JMenu m1 = new JMenu("File");
        menuBar.add(m1);
        JMenuItem exitButton = new JMenuItem("Exit");
        JMenuItem newButton = new JMenuItem("New portfolio");
        m1.add(newButton);
        m1.add(exitButton);
        this.setJMenuBar(menuBar);







        setVisible(true);
    }
}
