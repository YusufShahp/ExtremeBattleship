package com.example.springboot;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SampleHmlDom {

    private static String packageNamePath = "com/example/springboot";
    Document document = null;

    public void initialize(String file)
    {
        try
        {
            String currentClassPath = SampleHmlDom.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            
            // The getPath returns string with a "/" in front. Need to remove that.
            // The getPath returns string with a "/" in front. Need to remove that.
            String fixedClassPath = currentClassPath;
            if (currentClassPath.startsWith("/"))
            {
                fixedClassPath = currentClassPath.substring(1);
            }

            fixedClassPath = Paths.get(fixedClassPath, packageNamePath).toString();

            File htmlFile = new File(Paths.get(fixedClassPath, file).toString());
                
            this.document = Jsoup.parse(htmlFile, "UTF-8");

            Element elementGrid = this.document.getElementById("Grid");

            System.out.println("Got Grid element: " + elementGrid.nodeName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {}
    }

    String temp;
    String temp1;
    String temp2;
    String temp3;

    public void updateCellColor(int id1, int id2, String color)
    {
        temp1 = Integer.toString(id1);
        temp2 = Integer.toString(id2);
        temp3 = temp1 + temp2;

        Element cell = this.document.getElementById(temp3);
        temp = cell.className();
        cell.removeClass(temp);
        cell.addClass(color);


        // Attributes attributes = cell.attributes();
        // attributes.add("id", "33");

      /*  for (Attribute a : attributes)
        {
            System.out.println("Key,Value : " + a.getKey() + ". " + a.getValue());
        }*/
    }

    public void updateCellStatus(int id1, int id2, String circleType)
    {
        temp1 = Integer.toString(id1);
        temp2 = Integer.toString(id2);
        temp3 = "s" + temp1 + temp2;

        Element cell = this.document.getElementById(temp3);

        temp = cell.className();
        cell.removeClass(temp);
        cell.addClass(circleType);
    }
    
    public void deleteCellStatus(int id1, int id2)
    {
        temp1 = Integer.toString(id1);
        temp2 = Integer.toString(id2);
        temp3 = "s" + temp1 + temp2;

        Element cell = this.document.getElementById(temp3);

        temp = cell.className();
        cell.removeClass(temp);
    }

    public void updateText(boolean attacking)
    {
       /* String one = "Place";
        String two = "Your";
        String three = "Ships";
        String prompt = "green";

        if(attacking)
        {
            one = "Ships";
            two = "Left To";
            three = "Sink";
            prompt = "red";
        }

        Element cell1 = this.document.getElementById("text1");
        temp = cell1.className();
        cell1.removeClass(temp);
        cell1.addClass(prompt);
        cell1.html(one);

        Element cell2 = this.document.getElementById("text2");
        temp = cell2.className();
        cell2.removeClass(temp);
        cell2.addClass(prompt);
        cell2.html(two);

        Element cell3 = this.document.getElementById("text3");
        temp = cell3.className();
        cell3.removeClass(temp);
        cell3.addClass(prompt);
        cell3.html(three);*/
    }

    public void putStrike(String id)
    {
        Element cell = this.document.getElementById(id);
        temp = cell.className();
        cell.removeClass(temp);
        cell.addClass("done");
    }
    
    public void deleteStrike(String id)
    {
        Element cell = this.document.getElementById(id);
        temp = cell.className();
        cell.removeClass(temp);
    }

    public void writeHtmlToFile()
    {
        try
        {
            // Write the html to a file.
            Path currentRelativePath = Paths.get("");
            String currentPath = currentRelativePath.toAbsolutePath().toString();
            FileWriter writer = new FileWriter(Paths.get(currentPath, "updatedhtml.html").toString());
            writer.write(this.document.html());
            writer.close();
        }
        catch (Exception e) {}
        finally {}
    }

    public String getHtml()
    {
        return (this.document.html());
    }
    //new
    public String getHtmlBody()
    {
        Element cell = this.document.getElementById("body");
        return (cell.html());
    }
    //new
    public void putContext(String id, String context)
    {
        Element cell = this.document.getElementById(id);
        cell.html("");
        cell.html(context);
    }    

    public String getContext(String id)
    {
        Element cell = this.document.getElementById(id);
        return(cell.html());
    }

    public void showOption()
    {
        Element cell = this.document.getElementById("nextMovePane");
        Element cell1 = this.document.getElementById("rearrangeShipsPane");

        cell1.removeAttr("style");
        cell.attr("style", "display:none;");

    }

    public void over(boolean win)
    {
        Element cell = this.document.getElementById("nextMovePane");
        cell.attr("style", "display:none;");

        if(win)
        {
            Element cell1 = this.document.getElementById("gameOverWin");
            cell1.removeAttr("style");
        }
        else
        {
            Element cell1 = this.document.getElementById("gameOverLose");
            cell1.removeAttr("style");
        }

    }

    public void putGlow(int x, int y)
    {
        String temp1 = Integer.toString(y);
        String temp2 = Integer.toString(x);
        String temp3 = temp1 + temp2;

        Element cell = this.document.getElementById(temp3);
        String temp = cell.className();
        cell.removeClass(temp);
        temp = temp + "Blink";
        cell.addClass(temp);
    }
}