/**
 * Created with IntelliJ IDEA.
 * User: Arvind Kumar
 * Date: 7/5/12
 * Time: 2:16 PM
 * Description: AnalyzeGEDCOM2XML class used to test the functionality  of GEDCOM to XML
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AnalyzeGEDCOM2XML {

    public AnalyzeGEDCOM2XML()
    {
    }

    public static void main(String[] args) throws IOException, Exception
    {
        String gedcominputfilename = null;
        String xmloutputfilename = null;
        Properties prop = new Properties();


        try {
            //load a properties file
            prop.load(new FileInputStream("GEDCOM2XML.properties"));

            //get the property value
            gedcominputfilename = prop.getProperty("gedcominputfilename");
            xmloutputfilename   =  prop.getProperty("xmloutputfilename");

            System.out.println(gedcominputfilename);
            System.out.println(xmloutputfilename);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    if(gedcominputfilename == null ||  xmloutputfilename == null)

        {
            System.out.println("************************************************************************");
            System.out.println("Format: ");
            System.out.println("        java AnalyzeGEDCOM2XML <gedcominputfilename> <xmloutputfilename>");
            System.out.println("************************************************************************");
            System.exit(0);
        }

        GEDCOM2XML g2x = new GEDCOM2XML(gedcominputfilename,xmloutputfilename);
        g2x.convert();
    }

}
