package com.trading.app.db;

import lombok.Data;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @SneakyThrows
    @Test
     void test1() {
        Database database = Database.getInstance();
        database.update("TestUser", "TSLA", 4);
        Map<String, Map<String, Integer>> res = database.getAll();
        assertEquals(1, res.size());
        assertTrue(res.containsKey("TestUser"));
        database.update("TestUser", "APPL", 3);
        assertEquals(2, database.getAll().get("TestUser").size());
        assertTrue(res.get("TestUser").containsKey("TSLA"));
        assertEquals(4, res.get("TestUser").get("TSLA"));
        assertEquals(3, database.getAll().get("TestUser").get("APPL"));
        database.removeStock("TestUser", "TSLA");
        assertFalse(database.getAll().get("TestUser").containsKey("TSLA"));
        database.removeUser("TestUser");
        assertEquals(0, database.getAll().size());
    }
}
