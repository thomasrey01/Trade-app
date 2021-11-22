package com.trading.app.view.panels;

import javax.swing.*;
import java.awt.*;

public class TradePanel extends JPanel {

    private GridBagConstraints gbc;

    public TradePanel() {
        super(new GridBagLayout());
        gbc = new GridBagConstraints();
//        this.setLayout(new GridLayout(0, 1));
        initUI();
    }

    private void initUI() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel jLabel = new JLabel("Thomas's portfolio: ");
        jLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        this.add(jLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(buildHeaderLabel("Balance: "), gbc);
        gbc.gridx = 1;
        JTextArea balance = new JTextArea("$0");
        balance.setEditable(false);
        this.add(balance, gbc);
        gbc.gridx = 2;
        this.add(buildHeaderLabel("Trending: "), gbc);
        gbc.gridx = 3;
        JTextArea trending = new JTextArea("TSLA");
        trending.setEditable(false);
        this.add(trending, gbc);
        gbc.gridy = 2;

    }

    private JLabel buildHeaderLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, label.getFont().getSize() + 2));
        return label;
    }

}
