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
public class IfStatement extends Statement {
    private final Expression e;
    private final Statement statement;

    public IfStatement(Expression e, Statement statement, 
            int lineNumber, PrintStream outStream) {
        super(lineNumber, outStream);
        this.e = e;
        this.statement = statement;
        if (e.type != Type.BOOL) {
            throwError("a boolean expression type is required for if statements");
        }
    }

    @Override
    public void generate(int stmtBeginLabel, int labelAfterStatement) {
        //create new label
        int label = newLabel();
        //write iffalse statement
        e.jump(0, labelAfterStatement);
        //write label
        emitLabel(label);
        //write statements
        statement.generate(label, labelAfterStatement);
    }
    
}
