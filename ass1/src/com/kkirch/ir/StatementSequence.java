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
public class StatementSequence extends Statement {

    private final Statement s1, s2;

    public StatementSequence(Statement s1, Statement s2,
            int lineNumber, PrintStream outStream) {
        super(lineNumber, outStream);
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void generate(int stmtBeginLabel, int labelAfterStatement) {
        if (s1 == Statement.NULL) {
            s2.generate(stmtBeginLabel, labelAfterStatement);
        } else if (s2 == Statement.NULL) {
            s1.generate(stmtBeginLabel, labelAfterStatement);
        } else {
            int l1 = newLabel();
            s1.generate(stmtBeginLabel, l1);
            emitLabel(l1);
            s2.generate(l1, labelAfterStatement);
        }
    }
}
