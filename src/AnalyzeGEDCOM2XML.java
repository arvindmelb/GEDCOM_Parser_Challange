/**
 * Created with IntelliJ IDEA.
 * User: Arvind Kumar
 * Date: 7/5/12
 * Time: 2:16 PM
 * Description: AnalyzeGEDCOM2XML class used to test the functionality  of GEDCOM to XML
 */

import java.io.IOException;

public class AnalyzeGEDCOM2XML {

    public AnalyzeGEDCOM2XML()
    {
    }

    public static void main(String[] args) throws IOException, Exception
    {
        if(args.length < 2)
        {
            System.out.println("************************************************************************");
            System.out.println("Format: ");
            System.out.println("        java AnalyzeGEDCOM2XML <gedcominputfilename> <xmloutputfilename>");
            System.out.println("************************************************************************");
            System.exit(0);
        }

        GEDCOM2XML g2x = new GEDCOM2XML(args[0],args[1]);
        g2x.convert();
    }



}
