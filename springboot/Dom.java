package com.example.springboot;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.plaf.FileChooserUI;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLDocument.HTMLReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Dom {

    Document document = null;

    public void initialize()
    {
        try
        {
            Path currentRelativePath = Paths.get("");
            String currentPath = currentRelativePath.toAbsolutePath().toString();

            File htmlFile = new File(Paths.get(currentPath, "boardpanel.html").toString());
            this.document = convertHtmlFileToXMLDocument(htmlFile);
            this.document.getDocumentElement().normalize();
        }
        catch (Exception e)
        {}
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

        /*Element cell = this.document.getElementById(temp3);
        temp = cell.className();
        cell.removeClass(temp);
        cell.addClass(color);


        // Attributes attributes = cell.attributes();
        // attributes.add("id", "33");
        */


        System.out.println(this.document.getNodeName());

        NodeList nodeListTable = this.document.getElementsByTagName("table");
        System.out.println("Nodes with TagName = table");
        for (int count = 0; count < nodeListTable.getLength(); count++) 
        {
            Node node = nodeListTable.item(count);
            //Element element = (Element) node;
            //element.setIdAttribute("Grid", true);
            System.out.println(node.getNodeName());
        }

        NodeList nodeListTD = this.document.getElementsByTagName("td");
        System.out.println("Nodes with TagName = td");
        for (int count = 0; count < nodeListTD.getLength(); count++) 
        {
            Node node = nodeListTD.item(count);
            Element element = (Element) node;
            Node nodeChild = node.getFirstChild();
            Element elementChild = (Element) nodeChild;

            int i = 0;
            if (i == 0)
                element.setIdAttribute("00", true);
            else
                elementChild.setIdAttribute("00", true);
            System.out.println(node.getNodeName());
        }

        Element cellGrid  = this.document.getElementById("Grid");
        if (cellGrid != null)
        {
            // cell.removeAttribute("class");
            // cell.setAttribute("class", color);
            System.out.println("Found cell Grid");
        }

        Element cell00  = this.document.getElementById("00");
        if (cell00 != null)
        {
            System.out.println("Found cell 00");
        }
    }

    public void updateCellStatus(int id1, int id2, String circleType)
    {
       /* temp1 = Integer.toString(id1);
        temp2 = Integer.toString(id2);
        temp3 = "s" + temp1 + temp2;

        Element cell = this.document.getElementById(temp3);

        temp = cell.className();
        cell.removeClass(temp);
        cell.addClass(circleType);*/
    }
    
    public void deleteCellStatus(int id1, int id2)
    {
       /* temp1 = Integer.toString(id1);
        temp2 = Integer.toString(id2);
        temp3 = "s" + temp1 + temp2;

        Element cell = this.document.getElementById(temp3);

        temp = cell.className();
        cell.removeClass(temp);*/
    }

    public void updateText(boolean attacking)
    {
       /* String one = "Place";
        String two = "Your";
        String three = "Ships";
        String prompt = "green";

        if(attacking)
        {
            one = "Time";
            two = "To";
            three = "Attack";
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
       /* Element cell = this.document.getElementById(id);
        temp = cell.className();
        cell.removeClass(temp);
        cell.addClass("done");*/
    }
    
    public void deleteStrike(String id)
    {
       /* Element cell = this.document.getElementById(id);
        temp = cell.className();
        cell.removeClass(temp);*/
    }

    public void outputHtml()
    {
        try
        {
            Path currentRelativePath = Paths.get("");
            String currentPath = currentRelativePath.toAbsolutePath().toString();
            FileWriter writer = new FileWriter(Paths.get(currentPath, "updatedhtml.html").toString());
            writer.write(outHtml());
            writer.close();
        }
        catch (Exception e) {}
    }

    private Document convertHtmlFileToXMLDocument(File htmlFile) 
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
             
            //Parse the content to Document object
            Document doc = builder.parse(htmlFile);
            return doc;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return null;
    }  

    private String outHtml()
    {
        try
        {
            DOMSource domSource = new DOMSource(this.document);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(domSource, result);            
            return writer.toString();
        }
        catch (Exception e) {}

        return "";
    }

}
