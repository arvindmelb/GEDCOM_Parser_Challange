/**
 * Created with IntelliJ IDEA.
 * User: Arvind Kumar
 * Date: 7/5/12
 * Time: 11:36 AM
 * Description: XMLWriter class provides XML Writing functionality
 *              It takes GEDCOM parsed input and converts it into XML file
 */

import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class XMLWriter {

    private Writer out;

    public XMLWriter() throws IOException
    {
        // If output file is specified the result will be displayed at standard output
        out = new BufferedWriter (new OutputStreamWriter(System.out));
    }

    public XMLWriter(String strFilename) throws IOException
    {
        // XML output file where the result will be written
        if ( strFilename != null )
        {
            out = new BufferedWriter(new FileWriter(strFilename));
        }
    }

    public void createNode(Parse obj) throws IOException
    {
        // Write Nodes with Value normally parent node
        //   eg:     <name value="Elizabeth Alexandra Mary /Windsor/">

        indentation(obj);
        out.write("<"+obj.getStrTag());
        if( obj.getStrId() != null )
        {
            out.write(" id=\""+obj.getStrId()+"\"");
        }
        if( obj.getStrValue() != null )
        {
            out.write(" value=\""+obj.getStrValue()+"\"");
        }
        out.write(">\n");
    }

    public void childNode(Parse obj) throws IOException
    {
        // Write Nodes with Data normally child node
        //    eg:        <surn>Windsor</surn>

        indentation(obj);
        out.write("<"+obj.getStrTag()+">");
        if ( obj.getStrValue() != null )
        {
            out.write(obj.getStrValue());
        }
        out.write("</"+obj.getStrTag()+">\n");
    }

    public void endNode(Parse obj) throws IOException
    {
        // Write the closing node with proper indentation
        //    eg:          </indi>

        indentation(obj);
        out.write("</"+obj.getStrTag()+">\n");
        out.flush();
    }

    private void indentation(Parse obj) throws IOException
    {
        // Provide indentation nodes as per the Level
        //      eg:      <surn>Windsor</surn>

        String separator = "    ";
        for ( int i = 0 ; i<=obj.getIntLevel();i++ )
        {
            out.write (separator);
        }
    }

}
