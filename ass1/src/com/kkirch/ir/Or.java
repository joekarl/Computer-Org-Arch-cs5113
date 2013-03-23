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
public class Or extends Logical {

    public Or(Expression e1, Expression e2, 
            Token operator, int lineNumber, PrintStream outStream) {
        super(e1, e2, Logical.BoolTypeCheck, operator, lineNumber, outStream);
    }

    @Override
    public void jump(int ifLabel, int elseLabel) {
        int l1 = ifLabel != 0 ? ifLabel : newLabel();
        e1.jump(l1, 0);
        e2.jump(ifLabel, elseLabel);
        if (ifLabel == 0) {
            emitLabel(l1);
        }
    }
}
