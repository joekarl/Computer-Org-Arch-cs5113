/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.ir;

import com.kkirch.ir.Expression;
import com.kkirch.lexer.Token;
import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class Not extends Logical {

    public Not(Expression e1, Expression e2, 
            Token operator, int lineNumber, PrintStream outStream) {
        super(e1, e2, Logical.BoolTypeCheck, operator, lineNumber, outStream);
    }
    
    @Override
    public void jump(int ifLabel, int elseLabel) {
        e2.jump(ifLabel, elseLabel);
    }

    @Override
    public String toString() {
        return operator + " " + e2;
    }
}
