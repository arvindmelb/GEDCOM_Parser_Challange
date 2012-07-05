/**
 * Created with IntelliJ IDEA.
 * User: Arvind Kumar
 * Date: 7/5/12
 * Time: 12:23 PM
 * Description: GEDCOM2XML class provides GEDCOM to XML convert functionality
 *              It takes GEDCOM input, parses and converts it into XML file
 */

import java.util.Stack;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class GEDCOM2XML {

    private XMLWriter wr;			    // output xml file
    private String a_Root;   			// Root node
    private BufferedReader a_input;		// input file
    private Stack<Parse> a_NodeStack ;	// stack use to store nodes

    public GEDCOM2XML(String strInputFilename ) throws IOException
    {
        a_input = new BufferedReader(new FileReader(strInputFilename));

        // support for GEDCOM input file
        wr = new XMLWriter();

        a_NodeStack = new Stack<Parse>();
        a_Root = "-1 GEDCOM";
    }

    public GEDCOM2XML(String strInputFilename , String strOutputFilename) throws IOException
    {
        a_input = new BufferedReader(new FileReader(strInputFilename));

        // support for xml file output given and GEDCOME input file
        wr = new XMLWriter(strOutputFilename);

        a_NodeStack = new Stack<Parse>();
        a_Root = "-1 GEDCOM";
    }


    // Getters as required for access
    // No Setters requires at this point

    public BufferedReader getInput()
    {
        return a_input;
    }

    public Stack<Parse> getNodeStack()
    {
        return a_NodeStack;
    }

    public String getRootNode()
    {
        return a_Root;
    }

    // method which does the actual conversion

    public void convert() throws IOException
    {
        String strLine = null;

        // Root Node which is gedcom here this
        // will become your previous line
        String strPrevLine = getRootNode();
        Parse prev = new  Parse(strPrevLine);

        Parse curr = new  Parse(strPrevLine);
        while (true)
        {

            // Get current line from the input file
            strLine = getInput().readLine();

            if ( strLine !=null && strLine.trim().compareTo("") == 0 )
            {
                // This handles blank lines in the file
                continue;
            }

            // Parse the previous line to get Node elements
            curr = new  Parse(strLine);

            if ( strLine  == null )
            {
                // End condition to complete the current node and exit
                wr.childNode(prev);
                break;
            }

            // Previous Level is < Current Level
            if ( getNodeStack().empty() || prev.getIntLevel() < curr.getIntLevel() )
            {
                // Create the node and push on to stack as we need to close later
                wr.createNode(prev);
                getNodeStack().push(prev);
            }
            else
            if ( prev.getIntLevel() == curr.getIntLevel() )
            {
                // Previous Level is == Current Level
                // Process the child Node
                wr.childNode(prev);
            }
            else
            if ( prev.getIntLevel() > curr.getIntLevel() )
            {
                // Previous Level is > Current Level
                // Process the child Node
                wr.childNode(prev);

                // Process the end Nodes on the stack till Current Level
                // is <= Level of node in stack

                while ( (!getNodeStack().empty() && (curr.getIntLevel()
                        <= getNodeStack().peek().getIntLevel())) )
                {
                    wr.endNode(getNodeStack().pop());
                }
            }

            // Assign current line to previous
            prev = curr ;
        }

        // Process any Nodes in the stack which require closure
        while(!getNodeStack().empty())
        {
            wr.endNode(getNodeStack().pop());
        }
    }

}
