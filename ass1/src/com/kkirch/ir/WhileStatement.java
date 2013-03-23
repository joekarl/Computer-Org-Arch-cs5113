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
public class WhileStatement extends Statement {

    private Expression e;
    private Statement s;

    public WhileStatement(int lineNumber, PrintStream outStream) {
        super(lineNumber, outStream);
    }
    
    public void init(Expression e, Statement s) {
        this.e = e;
        this.s = s;
        if (e.type != Type.BOOL) {
            throwError("a boolean expression type is required for while statements");
        }
    }

    @Override
    public void generate(int stmtBeginLabel, int labelAfterStatement) {
        this.labelAfterStatement = labelAfterStatement;
        e.jump(0, labelAfterStatement);
        int label = newLabel();
        emitLabel(label);
        s.generate(label, stmtBeginLabel);
        emitString("goto L" + stmtBeginLabel);
    }
    
    
}
