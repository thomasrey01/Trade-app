package com.trading.app;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.trading.app.model.TradeModel;
import com.trading.app.view.TradeFrame;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        if (System.getProperty("os.name").contains("Mac")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }

        FlatDarculaLaf.setup();

        TradeFrame frame = new TradeFrame(new TradeModel());
    }
}
