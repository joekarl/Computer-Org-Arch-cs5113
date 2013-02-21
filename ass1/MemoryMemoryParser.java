
import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kkirch
 */
@BuildParseTree
public class MemoryMemoryParser extends BaseParser<Object> {
    Rule Program() {
        return ZeroOrMore(Line());
    }

    Rule Line() {
        return FirstOf(
            Sequence(
                Expression(),
                LineEnding(),
                Space().label("EOL")
            ),
            Sequence(
                Assignment(),
                LineEnding(),
                Space().label("EOL")
            )
        );
    }

    Rule Expression() {
        return Sequence(
            Factor(),
            ZeroOrMore(
                Op(),
                Expression()).label("rest")
        );
    }

    Rule Op() {
        return FirstOf(
                    MultOp(),
                    DivOp(),
                    AddOp(),
                    SubOp());
    }

    Rule AddOp() {
        return Ch('+');
    }

    Rule SubOp() {
        return Ch('-');
    }

    Rule MultOp() {
        return Ch('*');
    }

    Rule DivOp() {
        return Ch('/');
    }

    Rule Assignment() {
        return Sequence(
            Variable(),
            Ch('=').label("AssignOp"),
            Expression()
        );
    }

    Rule Factor() {
        return FirstOf(
            Number(),
            Variable(),
            Sequence('(', Expression(), ')')
        );
    }

    Rule Variable() {
        return OneOrMore(CharRange('a','z'));
    }

    Rule Number() {
        return OneOrMore(CharRange('0', '9'));
    }

    Rule LineEnding() {
        return Ch(';');
    }

    Rule Space() {
        return ZeroOrMore(FirstOf(

            // whitespace
            OneOrMore(AnyOf(" \t\r\n\f").label("Whitespace")),

            // traditional comment
            Sequence("/*", ZeroOrMore(TestNot("*/"), ANY), "*/"),

            // end of line comment
            Sequence(
                    "//",
                    ZeroOrMore(TestNot(AnyOf("\r\n")), ANY),
                    FirstOf("\r\n", '\r', '\n', EOI)
            )
        ));
    }
}
