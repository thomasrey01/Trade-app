package com.trading.app.control;
import net.jacobpeterson.alpaca.*;
import org.slf4j

public class APIControl {

    private final static String authToken = 

    private APIControl instance = null;

    private AlpacaAPI alpacaAPI;

    private APIControl() {
        alpacaAPI = new AlpacaAPI();
        initConnection();
    }

    public APIControl getInstance() {
        if (instance == null) {
            instance = new APIControl();
        }
        return instance;
    }

    private void initConnection() {
        alpacaAPI.
    }
}
