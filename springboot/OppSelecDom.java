package com.example.springboot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class OppSelecDom {

    private static String packageNamePath = "com/example/springboot";
    Document document = null;

    public void initialize(String name)
    {
        try
        {
            /*Path currentRelativePath = Paths.get("");
            String path = System.getProperty("user.dir");

            String currentPath = currentRelativePath.toAbsolutePath().toString();

            currentPath = "C:/Users/yusuf/OneDrive/Documents/Programing/Springboot2/gs-spring-boot/complete/src/main/java/com/example/springboot/";*/

            String currentClassPath = WelcomePageDom.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            
            // The getPath returns string with a "/" in front. Need to remove that.
            String fixedClassPath = currentClassPath;
            if (currentClassPath.startsWith("/"))
            {
                fixedClassPath = currentClassPath.substring(1);
            }

            fixedClassPath = Paths.get(fixedClassPath, packageNamePath).toString();

            File htmlFile = new File(Paths.get(fixedClassPath, name).toString());
                
            this.document = Jsoup.parse(htmlFile, "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {}
    }

    public String getHtml()
    {
        return (this.document.html());
    }

    public void putText(String id, String text)
    {
        Element cell = this.document.getElementById(id);
        cell.html("");
        cell.html(text);
    }  
} 
