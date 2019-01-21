package server.models.service;

import server.Console;
import server.DatabaseConnection;
import server.models.Skill;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SkillService {

    public static String selectAllInto(List<Skill> targetList) {
        targetList.clear();
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT id, skillName, keyAbility FROM Skill"
            );
            if (statement != null) {
                ResultSet results = statement.executeQuery();
                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Skill(results.getInt("id"), results.getString("skillName"), results.getString("keyAbility")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select all from 'Skill' table: " + resultsException.getMessage();

            Console.log(error);
            return error;
        }
        return "OK";
    }

    public static Skill selectById(int id) {
        Skill result = null;
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "SELECT id, skillName, keyAbility FROM Skill WHERE id = ?"
            );
            if (statement != null) {
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                if (results != null && results.next()) {
                    result = new Skill(results.getInt("id"), results.getString("skillName"), results.getString("keyAbility"));
                }
            }
        } catch (SQLException resultsException) {
            String error = "Database error - can't select by id from 'Skill' table: " + resultsException.getMessage();

            Console.log(error);
        }
        return result;
    }

    public static String insert(Skill itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "INSERT INTO Skill (id, skillName, keyAbility) VALUES (?, ?, ?)"
            );
            statement.setInt(1, itemToSave.getId());
            statement.setString(2, itemToSave.getSkillName());
            statement.setString(3, itemToSave.getKeyAbility());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't insert into 'Skill' table: " + resultsException.getMessage();

            Console.log(error);
            return error;
        }
    }

    public static String update(Skill itemToSave) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "UPDATE Skill SET skillName = ?, keyAbility = ? WHERE id = ?"
            );
            statement.setString(1, itemToSave.getSkillName());
            statement.setString(2, itemToSave.getKeyAbility());
            statement.setInt(3, itemToSave.getId());
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't update 'Skill' table: " + resultsException.getMessage();

            Console.log(error);
            return error;
        }
    }

    public static String deleteById(int id) {
        try {
            PreparedStatement statement = DatabaseConnection.newStatement(
                    "DELETE FROM Skill WHERE id = ?"
            );
            statement.setInt(1, id);
            statement.executeUpdate();
            return "OK";
        } catch (SQLException resultsException) {
            String error = "Database error - can't delete by id from 'Skill' table: " + resultsException.getMessage();

            Console.log(error);
            return error;
        }
    }

}
