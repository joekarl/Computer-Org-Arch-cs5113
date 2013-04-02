/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.ir;

import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class FunctionDeclerations extends Statement {

    private final Statement f1, f2;

    public FunctionDeclerations(Statement f1, Statement f2,
            int lineNumber, PrintStream outStream) {
        super(lineNumber, outStream);
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public void generate(int stmtBeginLabel, int labelAfterStatement) {
        if (f1 == Statement.NULL) {
            f2.generate(stmtBeginLabel, labelAfterStatement);
        } else if (f2 == Statement.NULL) {
            f1.generate(stmtBeginLabel, labelAfterStatement);
        } else {
            f1.generate(stmtBeginLabel, labelAfterStatement);
            int l1 = newLabel();
            int l2 = newLabel();
            emitLabel(l1);
            f2.generate(l1, l2);
            emitLabel(l2);
            emitString("");
        }
    }
}
