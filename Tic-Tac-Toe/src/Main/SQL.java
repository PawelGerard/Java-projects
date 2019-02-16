
package Main;

import Model.Game;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQL {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite::resource:games.db";
    private Statement statement;
    private Connection connection;

    public SQL() {
        try {
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        createTables();
    }

    public void createTables() {
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS games (id_game INTEGER PRIMARY KEY AUTOINCREMENT, type varchar(255), boardSize varchar(255), player1 varchar(255), player2 varchar(255), gameResult varchar(255), winner varchar(255), time real);");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Game> selectGames() {
        ArrayList<Game> list = new ArrayList<Game>();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM games ORDER BY id_game DESC;");
            while (result.next()) {
                int id = result.getInt("id_game");
                String type = result.getString("type");
                String boardSize = result.getString("boardSize");
                String player1 = result.getString("player1");
                String player2 = result.getString("player2");
                String gameResult = result.getString("gameResult");
                String winner = result.getString("winner");
                float time = result.getFloat("time");
                list.add(new Game(id, type, boardSize, player1, player2, gameResult, winner, time));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertGame(String type, String boardSize, String player1, String player2, String gameResult, String winner, float time) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO games VALUES(null, ?, ?, ?, ?, ?, ?, ?);");
            ps.setString(1, type);
            ps.setString(2, boardSize);
            ps.setString(3, player1);
            ps.setString(4, player2);
            ps.setString(5, gameResult);
            ps.setString(6, winner);
            ps.setFloat(7, time);
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getGameCount() {
        int count = -1;
        try {
            ResultSet result = statement.executeQuery("SELECT COUNT(*) AS Count FROM games");
            count = result.getInt("Count");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getCircleVictories() {
        int count = -1;
        try {
            ResultSet result = statement.executeQuery("SELECT COUNT(*) AS Count FROM games WHERE winner=player1");
            count = result.getInt("Count");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getCrossVictories() {
        int count = -1;
        try {
            ResultSet result = statement.executeQuery("SELECT COUNT(*) AS Count FROM games WHERE winner=player2");
            count = result.getInt("Count");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public float getShortestGame() {
        float count = -1.0f;
        try {
            ResultSet result = statement.executeQuery("SELECT MIN(time) AS minTime FROM games");
            count = result.getFloat("minTime");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public float getLongestGame() {
        float count = -1.0f;
        try {
            ResultSet result = statement.executeQuery("SELECT MAX(time) AS maxTime FROM games");
            count = result.getFloat("maxTime");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public String getBestPlayer() {
        String bestPlayer = null;
        try {
            ResultSet result = statement.executeQuery("SELECT winner, COUNT(*) AS Count FROM games GROUP BY winner ORDER BY Count DESC LIMIT 1");
            bestPlayer = String.valueOf(result.getString("winner")) + " " + result.getInt("Count");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return bestPlayer;
    }

    public String getBestPlayerAsideAI() {
        String bestPlayer = null;
        try {
            ResultSet result = statement.executeQuery("SELECT winner, COUNT(*) AS Count FROM games WHERE winner!='AI' GROUP BY winner ORDER BY Count DESC LIMIT 1");
            bestPlayer = String.valueOf(result.getString("winner")) + " " + result.getInt("Count");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return bestPlayer;
    }
}

