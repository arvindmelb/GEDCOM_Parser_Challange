/**
 * Created with IntelliJ IDEA.
 * User: Arvind Kumar
 * Date: 7/5/12
 * Time: 10:18 AM
 * Description: Parse class provides Parsing functionality
 *              It takes GEDCOM input line and converts it into
 *              LEVEL, TAG or ID , VALUE and or DATA
 */
public class Parse {
    private String a_strId;
    private String a_strTag;
    private String a_strValue;
    private String a_strLevel;
    private String a_strLine;


    public Parse(String strLine)
    {
        a_strLine = a_strId = a_strTag = a_strValue = null;
        if (strLine != null )
        {
            a_strLine = strLine ;
            initIdTagValue();
        }
    }


    private void initIdTagValue()
    {

        // This method  which does the
        // work of fetching LEVEL, ID, VALUE from
        // the given line

        // Skip white spaces and getting the tokens
        String[] tokens = a_strLine.split("\\s+", 3);

        a_strLevel = tokens[0];
        if(tokens[1].startsWith("@") && tokens[1].endsWith("@"))
        {
            // Extract ID
            a_strId = tokens[1];
            a_strTag = tokens[2].toLowerCase();
        }
        else if(tokens.length == 3)
        {
            // We have LEVEL, ID/TAG and VALUE here
            a_strTag = tokens[1].toLowerCase();
            a_strValue = tokens[2];
        }
        else
        {
            a_strTag = tokens[1].toLowerCase();
        }
    }

    // Getters methods for required member variables

    public String getStrId()
    {
        return a_strId;
    }

    public String getStrLevel()
    {
        return a_strLevel;
    }

    public int getIntLevel()
    {
        // Get integer value of LEVEL
        return Integer.parseInt(a_strLevel);
    }

    public String getStrTag()
    {
        return a_strTag;
    }

    public String getStrValue()
    {
        return a_strValue;
    }

}
