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
public class ArithmeticExpression extends Operator {

    private final Expression e1, e2;

    public ArithmeticExpression(Expression e1, Expression e2,
            Token operator, int lineNumber, PrintStream outStream) {
        //do type coercion 
        super(operator, Type.typeConvert(e1.type, e2.type), lineNumber, outStream);

        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Expression generate() {
        //create a new expression with the results of the 2 input expressions
        return new ArithmeticExpression(e1.reduce(), e2.reduce(),
                operator, lineNumber, outStream);
    }

    @Override
    public String toString() {
        return e1.toString() + " " + operator.toString() + " " + e2.toString();
    }
}
