/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.ir;

import com.kkirch.lexer.Token;
import com.kkirch.symbols.Type;
import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class Operator extends Expression {

    public Operator(Token operator, Type type, int lineNumber, PrintStream outStream) {
        super(operator, type, lineNumber, outStream);
    }

    @Override
    public Expression reduce() {
        Expression x = generate();
        Temp t = new Temp(type, lineNumber, outStream);
        emitString(t.toString() + " = " + x.toString());
        return t;
    }
}
