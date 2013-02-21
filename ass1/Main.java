
import org.parboiled.Parboiled;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParsingResult;
import org.parboiled.support.ParseTreeUtils;
import org.parboiled.Node;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author kkirch
 */
public class Main {

    public static void main(String... args) {
        String input = ""
            + "1+2*8-10;\n"
            + "1*3;\n"
            + "c=a+b;";
        MemoryMemoryParser parser = Parboiled.createParser(MemoryMemoryParser.class);
        ParsingResult<?> result = ReportingParseRunner.run(parser.Program(), input);
        String parseTreePrintOut = ParseTreeUtils.printNodeTree(result);
        System.out.println(parseTreePrintOut);
    }
}
