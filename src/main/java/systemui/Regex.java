/**
 * @author Group 11
 */
package systemui;

public class Regex {
    //Regex to validate user input
    public final static String NUM_1_TO_6 = "[1-6]";
    public final static String NUM_1_TO_8 = "[1-8]";
    public final static String NUM_1_TO_5 = "[1-5]";
    public final static String NUM_1_OR_2 = "[1-2]";
    public final static String NUM_1_TO_3 = "[1-3]";
    public final static String INTEGER_NUMBER = "[0-9]+";
    public final static String DOUBLE_NUMBER = "[0-9]{1,13}(\\.[0-9]*)?";
    public final static String TRUE_OR_FALSE = "true|false";
    public final static String TAX_TYPE = "FREE|NORMAL|LUXURY";
}
