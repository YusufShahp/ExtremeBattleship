package com.example.springboot;

import java.util.Scanner;  

public class GameRecord 
{
    public boolean bothConnected;
    int ID;
    public String turn = "one";

    public int latestGuessX;
    public int latestGuessY;
    public String latestPlayerToGuess;

    private String player1phase;
    private String player2phase;
    public GameRecord(int ID)
    {
        this.ID = ID;
    }

    private String player1Grid;
    private String player2Grid;

    private String attacker1;
    private String attacker2;

    private String player1panel;
    private String player2panel;

    public void setGrid(String playerNumber, String grid)
    {
        if(playerNumber.equals("one"))
        {
            player1Grid = grid;
        }
        else
        {
            player2Grid = grid;
        }
    }

    public void setAttacker(String playerNumber, String temp)
    {
        if(playerNumber.equals("one"))
        {
            attacker1 = temp;
        }
        else
        {
            attacker2 = temp;
        }
    }

    public void setPanel(String playerNumber, String temp)
    {
        if(playerNumber.equals("one"))
        {
            player1panel = temp;
        }
        else
        {
            player2panel = temp;
        }
    }

    public void setPhase(String playerNumber, String phase)
    {
        if(playerNumber.equals("one"))
        {
            player1phase = phase;
        }
        else
        {
            player2phase = phase;
        }
    }

    public int getID()
    {
        return ID;
    }

    public String getPhase(String playerNumber)
    {
        if(playerNumber.equals("one"))
        {
            return player1phase;
        }
        else
        {
            return player2phase;
        }
    }

    public String getGrid(String playerNumber)
    {
        if(playerNumber.equals("one"))
        {
            return player1Grid;
        }
        else
        {
            return player2Grid;
        }
    }

    public String getAttacker(String playerNumber)
    {
        if(playerNumber.equals("one"))
        {
            return attacker1;
        }
        else
        {
            return attacker2;
        }
    }

    public String getPanel(String playerNumber)
    {
        if(playerNumber.equals("one"))
        {
            return player1panel;
        }
        else
        {
            return player2panel;
        }
    }

    public String completeSerialization()
    {
        return ("" + bothConnected + "XXXX"
        + ID + "XXXX"
        + turn + "XXXX"
        + latestGuessX + "XXXX"
        + latestGuessY + "XXXX"
        + latestPlayerToGuess + "XXXX"
        + player1phase + "XXXX"
        + player2phase + "XXXX"
        + player1Grid + "XXXX"
        + player2Grid + "XXXX"
        + attacker1 + "XXXX"
        + attacker2 + "XXXX"
        + player1panel + "XXXX"
        + player2panel);
    }

    public void completePopulate(String serialized)
    {
        Scanner scan = new Scanner(serialized);
        scan.useDelimiter("XXXX");
        String temp = scan.next();
        if(temp.equals("true"))
        {
            bothConnected = true;
        }
        else
        {
            bothConnected = false;
        }

        ID = Integer.parseInt(scan.next());

        turn = scan.next();
        if(turn.equals("null"))
        {
            turn = null;
        }

        latestGuessX = Integer.parseInt(scan.next());
        // if(latestGuessX = null)
        // {
        //     turn = null;
        // }

        latestGuessY = Integer.parseInt(scan.next());
        // if(turn.equals("null" || "0"))
        // {
        //     turn = null;
        // }

        latestPlayerToGuess = scan.next();
        if(latestPlayerToGuess.equals("null"))
        {
            latestPlayerToGuess = null;
        }

        player1phase = scan.next();
        if(player1phase.equals("null"))
        {
            player1phase = null;
        }

        player2phase = scan.next();
        if(player2phase.equals("null"))
        {
            player2phase = null;
        }

        player1Grid = scan.next();
        if(player1Grid.equals("null"))
        {
            player1Grid = null;
        }
        
        player2Grid = scan.next();
        if(player2Grid.equals("null"))
        {
            player2Grid = null;
        }

        attacker1 = scan.next();
        if(attacker1.equals("null"))
        {
            attacker1 = null;
        }

        attacker2 = scan.next();
        if(attacker2.equals("null"))
        {
            attacker2 = null;
        }

        player1panel = scan.next();
        if(player1panel.equals("null"))
        {
            player1panel = null;
        }

        player2panel = scan.next();
        if(player2panel.equals("null"))
        {
            player2panel = null;
        }
    }
}
