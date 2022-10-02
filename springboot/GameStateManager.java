package com.example.springboot;

import java.util.Random;
import java.util.ArrayList;

public class GameStateManager 
{
    private static GameStateManager singletonGameStateManager;
    ArrayList<GameRecord> GameRecordList = new ArrayList<GameRecord>();

    private GameStateManager(){}
    
    public static GameStateManager getInstance()
    {
        if (singletonGameStateManager == null)
        {
            singletonGameStateManager = new GameStateManager();
        }

        return singletonGameStateManager;
    }

    public GameRecord GetGameRecord(int gameID)
    {
        if (gameID == -1)
        {
            Random rand = new Random();
            GameRecord record = new GameRecord(rand.nextInt(10000));
            GameRecordList.add(record);
            DatabaseHandler.makeNewGame(record.ID, record.completeSerialization());

            return record;
        }
        else
        {
            ArrayList<GameRecord> gameRecords = new ArrayList<GameRecord>();
            DatabaseHandler.setGameRecordData(gameRecords);
            for(int i = 0; i < gameRecords.size(); i++)
            {
                if(gameRecords.get(i).getID() == gameID)
                {
                    return gameRecords.get(i);
                }
            }
        }
        //GameRecord temp = new GameRecord(-5);
        //System.out.println("error");
        return null;
        // GameRecord - object that stores 
        // To start with, for the local host scenario we will store 
        // GameRecords as in-memory list. They will disappear when WebServer process ends.
        // TODO: return gameList.Get(gameId);

        // Eventually instead for an in-memory list, we will read from DB.
        // TODO: return DB.getRecord(gameId);
    }
 
}
