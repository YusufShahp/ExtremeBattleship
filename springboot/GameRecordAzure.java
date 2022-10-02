package com.example.springboot;

// import com.microsoft.azure.storage.*;
// import com.microsoft.azure.storage.table.*;
// import com.microsoft.azure.storage.table.TableQuery.*;

public class GameRecordAzure //extends TableServiceEntity 
{
    String attacker;
    String grid;
    int guessX;
    int guessY;
    String panel;
    String phase;
    public GameRecordAzure(String gameID, String playerNumber) 
    {
        //this.partitionKey = gameID;
        //this.rowKey = playerNumber;
    }

    public GameRecordAzure() {}

    public String getattacker() {
        return this.attacker;
    }

    public void setAttacker(String attacker) {
        this.attacker = attacker;
    }

    public String getGrid() {
        return this.grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public String getPhase() {
        return this.phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getPanel() {
        return this.panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;
    }

    public int getGuessX() {
        return this.guessX;
    }

    public void setGuessX(int guessX) {
        this.guessX = guessX;
    }

    public int getGuessY() {
        return this.guessY;
    }

    public void setGuessY(int guessY) {
        this.guessY = guessY;
    }
}
