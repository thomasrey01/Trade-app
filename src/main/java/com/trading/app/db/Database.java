package com.trading.app.db;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private static String url = "jdbc:sqlite:src/resources/db/preferences.db";

    private final Connection connection;

    private static Database databaseInstance = null;

    private Database() {
        connection = connect();
        assert(connection != null);
        createTable();
    }

    public static Database getInstance() {
        if (databaseInstance == null) {
            databaseInstance = new Database();
        }
        return databaseInstance;
    }

    private Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS preferences(\n"
                + "userID text NOT NULL,\n"
                + "symbol text NOT NULL,\n"
                + "amount integer NOT NULL);";
        if (connection == null) {
            return;
        }
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(String userID, String stockSymbol, int amount) throws SQLException {
        assert(connection != null);
        String sql1 = "INSERT INTO preferences(userID, symbol, amount) VALUES(?,?,?)";
        String sql2 = "UPDATE preferences SET amount = ? WHERE userID = ?";
        Map<String, Map<String, Integer>> resMap = getAll();
        PreparedStatement statement;
        if (!resMap.containsKey(userID) || !resMap.get(userID).containsKey(stockSymbol)) {
            statement = connection.prepareStatement(sql1);
            statement.setString(1, userID);
            statement.setString(2, stockSymbol);
            statement.setInt(3, amount);
        } else {
            statement = connection.prepareStatement(sql2);
            statement.setInt(1, amount);
            statement.setString(2, userID);
        }
        statement.executeUpdate();
    }

    public Map<String, Map<String, Integer>> getAll() throws SQLException {
        assert(connection != null);
        String sql = "SELECT * FROM preferences";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        Map<String, Map<String, Integer>> resultMap = new HashMap<>();
        Map<String, Integer> stocksMap;
        while (resultSet.next()) {
            String id = resultSet.getString("userID");
            String stock = resultSet.getString("symbol");
            int amount = resultSet.getInt("amount");
            if (resultMap.containsKey(id)) {
                stocksMap = resultMap.get(id);
            } else {
                stocksMap = new HashMap<>();
            }
            stocksMap.put(stock, amount);
            resultMap.put(id, stocksMap);
        }
        return resultMap;
    }

    public void removeUser(String userID) {
        assert(connection != null);
        String sql = "DELETE FROM preferences WHERE userID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeStock(String userID, String stockSymbol) {
        assert(connection != null);
        String sql = "DELETE FROM preferences WHERE userID = ? AND symbol = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, stockSymbol);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
