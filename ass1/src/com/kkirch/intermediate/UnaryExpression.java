/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.intermediate;

import com.kkirch.lexer.Token;
import com.kkirch.symbols.Type;
import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class UnaryExpression extends Operator {

    public Expression e;

    public UnaryExpression(Expression e, 
            Token t, int lineNumber, PrintStream outStream) {
        super(t, Type.typeConvert(Type.INT, e.type), lineNumber, outStream);
        this.e = e;
    }

    @Override
    public Expression generate() {
        return new UnaryExpression(e.reduce(), operator, lineNumber, outStream);
    }

    @Override
    public String toString() {
        return operator.toString() + " " + e.toString();
    }
}
