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
public class And extends Logical {

    public And(Expression e1, Expression e2, 
            Token operator, int lineNumber, PrintStream outStream) {
        super(e1, e2, Logical.BoolTypeCheck, operator, lineNumber, outStream);
    }
    
    @Override
    public void jump(int ifLabel, int elseLabel) {
        int l1 = elseLabel != 0 ? elseLabel : newLabel();
        e1.jump(0, l1);
        e2.jump(ifLabel, elseLabel);
        if (elseLabel == 0) {
            emitLabel(l1);
        }
    }
    
}
