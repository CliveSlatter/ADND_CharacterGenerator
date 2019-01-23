package server.models.service;

import server.Console;
import server.DatabaseConnection;
import server.models.AbilityBonus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbilityBonusService{
    public static int selectByBase(int base) {
        AbilityBonus result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT base, bonus FROM abilityBonus WHERE base = ?"
            );
            if (statement != null) {
                statement.setInt(1, base);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new AbilityBonus(results.getInt("base"), results.getInt("bonus"));
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'abilityBonus' table: " + resultsException.getMessage();

            Console.log(error);
        }
        return result.getBonus();
    }
}

