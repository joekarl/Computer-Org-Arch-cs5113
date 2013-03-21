/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.ir;

import com.kkirch.symbols.Type;
import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class IfElseStatement extends Statement {

    private final Expression e;
    private final Statement s1, s2;

    public IfElseStatement(Expression e, Statement s1, Statement s2,
            int lineNumber, PrintStream outStream) {
        super(lineNumber, outStream);
        this.e = e;
        this.s1 = s1;
        this.s2 = s2;
        if (e.type != Type.BOOL) {
            throwError("a boolean expression type is required for if statements");
        }
    }

    @Override
    public void generate(int stmtBeginLabel, int labelAfterStatement) {
        int l1 = newLabel();
        int l2 = newLabel();
        //write iffalse
        e.jump(0, l2);
        //write l1 label
        emitLabel(l1);
        //write s1 expressions
        s1.generate(l1, labelAfterStatement);
        //write jump out of if
        emitString("goto L" + labelAfterStatement);
        //write l2 label
        emitLabel(l2);
        //write s2 expressions
        s2.generate(l2, labelAfterStatement);
    }
}
