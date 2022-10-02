package com.example.springboot;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.ArrayList;

public class DatabaseHandler 
{
    private final static String connectionToUsersUrl; //Not posted on Github
    // private static final Logger log;

    // static
    // {
    // }
    // static {
    //     System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
    //     log =Logger.getLogger(DatabaseHandler.class.getName());
    // }

    // public static void organize throws Exception {
    //     log.info("Loading application properties");
    //     Properties properties = new Properties();
    //     properties.load(DemoApplication.class.getClassLoader().getResourceAsStream("application.properties"));

    //     log.info("Connecting to the database");
    //     Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
    //     log.info("Database connection test: " + connection.getCatalog());

    //     log.info("Create database schema");
    //     Scanner scanner = new Scanner(DemoApplication.class.getClassLoader().getResourceAsStream("schema.sql"));
    //     Statement statement = connection.createStatement();
    //     while (scanner.hasNextLine()) {
    //         statement.execute(scanner.nextLine());
    //     }

    public static void makeNewGame(int ID, String gameRecord)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        String insertSql = "INSERT INTO [BattleshipDB].dbo.MainTable (ID, GameRecord) VALUES ('"+ ID + "','" + gameRecord + "')";

        try (Connection connection = DriverManager.getConnection(connectionToUsersUrl); PreparedStatement prepsInsertProduct 
        = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) 
        {
            prepsInsertProduct.execute();
        }
        catch (Exception e) 
        {
            System.out.println("an error occured: " + e.toString());
        }
    }

    public static void update(int ID, String gameRecord)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        String insertSql = "UPDATE MainTable SET gameRecord = '"+gameRecord+"' WHERE ID = " + ID + ";";
        
        try (Connection connection = DriverManager.getConnection(connectionToUsersUrl);
                PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) 
        {
            prepsInsertProduct.execute();
            System.out.println(prepsInsertProduct.getUpdateCount());
        }
        catch (Exception e) 
        {
            System.out.println("an error occured: " + e.toString());
        }
    }

    public static void setGameRecordData(ArrayList<GameRecord> gameRecords)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        ResultSet entries;
        try (Connection con = DriverManager.getConnection(connectionToUsersUrl); Statement stat = con.createStatement();) 
        {
            entries = stat.executeQuery("SELECT * FROM [BattleshipDB].dbo.MainTable");

            while (entries.next()) 
            {
                GameRecord game = new GameRecord(entries.getInt(1));
                game.completePopulate(entries.getString(2));
                gameRecords.add(game);
            }
        }
        catch (SQLException e) 
        {
            System.out.println("an error occured: " + e.toString());
        }
    }
}
