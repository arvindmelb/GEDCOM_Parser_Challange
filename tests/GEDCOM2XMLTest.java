/**
* Created with IntelliJ IDEA.
* User: Arvind Kumar
* Date: 7/5/12
* Time: 4:57 PM
* Description: GEDCOM2XMLTest class is used to test the functionality of GEDCOM to XML file
*/

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.util.Properties;


public class GEDCOM2XMLTest {
    @Before
    public void setUp() throws Exception {
        System.out.println("\nIn GEDCOM2XMLTest::setUp");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("In GEDCOM2XMLTest::tearDown");
    }

    @Test
    public void testForVariableWhiteSpaceBtLevelAndTag() throws IOException, Exception {

        System.out.println("In GEDCOM2XMLTest::WhiteSpacebtLevelAndTag");

        /*
        Test to Check for Variable White Space between Level and Tag are ignored as
        specified in the requirement document
        */

        String TC_Input_WhiteSpaceBetweenLevelAndTagFile = null;
        String TC_ActualOutput_WhiteSpaceBetweenLevelAndTagFile = null;
        String TC_ExpectedResult_WhiteSpaceBetweenLevelAndTagFile = null;
        Properties prop = new Properties();


        try {
            //load a properties file
            prop.load(new FileInputStream("test.properties"));

            //get the property value
            String strInputFile  = prop.getProperty("TC_Input_WhiteSpaceBetweenLevelAndTagFile");
            String strOutputFile = prop.getProperty("TC_ActualOutput_WhiteSpaceBetweenLevelAndTagFile");
            String strResultFile = prop.getProperty("TC_ExpectedResult_WhiteSpaceBetweenLevelAndTagFile");

            System.out.println("INPUT FILE:           " + strInputFile);
            System.out.println("ACTUAL OUTPUT FILE:   " + strOutputFile);
            System.out.println("EXPECTED OUTPUT FILE: " + strResultFile);



            if(strInputFile == null ||  strOutputFile== null || strResultFile == null)

            {
                System.out.println("************************************************************************");
                System.out.println("Error: ");
                System.out.println("                testForVariableWhiteSpaceBtLevelAndTag files not defined");
                System.out.println("************************************************************************");
                System.exit(0);
            }

            convertGEMCOM2XML(strInputFile,strOutputFile,strResultFile);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testForBlankLines() throws IOException, Exception {
        System.out.println("In GEDCOM2XMLTest::testBlankLines");

        /*
        Test to check for Blank Lines in the input file are ignored as
        specified in the requirement document
        */

        String TC_Input_BlankLinesFile = null;
        String TC_ActualOutput_BlankLinesFile = null;
        String TC_ExpectedResult_BlankLinesFile = null;
        Properties prop = new Properties();


        try {
            //load a properties file
            prop.load(new FileInputStream("test.properties"));

            //get the property value
            String strInputFile  = prop.getProperty("TC_Input_BlankLinesFile");
            String strOutputFile = prop.getProperty("TC_ActualOutput_BlankLinesFile");
            String strResultFile = prop.getProperty("TC_ExpectedResult_BlankLinesFile");

            System.out.println("INPUT FILE:           " + strInputFile);
            System.out.println("ACTUAL OUTPUT FILE:   " + strOutputFile);
            System.out.println("EXPECTED OUTPUT FILE: " + strResultFile);



            if(strInputFile == null ||  strOutputFile== null || strResultFile == null)

            {
                System.out.println("************************************************************************");
                System.out.println("Error: ");
                System.out.println("                  testForBlankLines files not defined in property file"  );
                System.out.println("************************************************************************");
                System.exit(0);
            }

            convertGEMCOM2XML(strInputFile,strOutputFile,strResultFile);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testSampleDataValues() throws IOException, Exception {
        System.out.println("In GEDCOM2XMLTest::testSampleDataValues");

        /*
           Test which check
           1> "0 @I1@ INDI" get converted to    <indi id="@I1@">
           2> "1 NAME Jamis Gordon /Buck/" get converted to <name value="Jamis Gordon /Buck/">
           3> "2 SURN Buck" get converted to <surn>Buck</surn>
           4> "1 SEX M" get converted to <sex>M</sex>
         */


        String TC_Input_SampleDataValuesFile = null;
        String TC_ActualOutput_SampleDataValuesFile = null;
        String TC_ExpectedResult_SampleDataValuesFile = null;
        Properties prop = new Properties();


        try {
            //load a properties file
            prop.load(new FileInputStream("test.properties"));

            //get the property value
            String strInputFile  = prop.getProperty("TC_Input_SampleDataValuesFile");
            String strOutputFile = prop.getProperty("TC_ActualOutput_SampleDataValuesFile");
            String strResultFile = prop.getProperty("TC_ExpectedResult_SampleDataValuesFile");

            System.out.println("INPUT FILE:           " + strInputFile);
            System.out.println("ACTUAL OUTPUT FILE:   " + strOutputFile);
            System.out.println("EXPECTED OUTPUT FILE: " + strResultFile);



            if(strInputFile == null ||  strOutputFile== null || strResultFile == null)

            {
                System.out.println("************************************************************************");
                System.out.println("Error: ");
                System.out.println("             testSampleDataValues files not defined in property file"    );
                System.out.println("************************************************************************");
                System.exit(0);
            }

            convertGEMCOM2XML(strInputFile,strOutputFile,strResultFile);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void convertGEMCOM2XML( String strInputFile,
                                       String strOutputFile, String strResultFile ) throws IOException {
        System.out.println("In GEDCOM2XMLTest::convertGEMCOM2XML");

        /* Does the actual conversion from GEDCOM input file to XML output file */

        GEDCOM2XML g2x = new GEDCOM2XML(strInputFile,strOutputFile);
        g2x.convert();

        assertTrue("Both Expected Output and Actual Result should be same",
                compareExpectedWithActual(strOutputFile,strResultFile));

        File fileOutput = new File(strOutputFile);
    }

    public static boolean compareExpectedWithActual(String strExpectedOutputFile,String strActualResultFile)
            throws IOException {
        System.out.println("In GEDCOM2XMLTest::compareExpectedWithActual");

        /* To compare ActualResult file with ExpectedOutput file */

        BufferedReader brExpectedOutput = new BufferedReader(new FileReader(strExpectedOutputFile));
        BufferedReader brActualResult = new BufferedReader(new FileReader(strActualResultFile));

        String strLine;
        while((strLine = brExpectedOutput.readLine()) != null)
        {
            if(!strLine.equals(brActualResult.readLine()))
            {
                return false;
            }
        }
        if(brActualResult.readLine() != null)
        {
            return false;
        }
        return true;
    }

}
