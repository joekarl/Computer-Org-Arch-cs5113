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
public class Function extends Statement {
    private final Id id;
    private final Statement stmt;

    public Function(Id id, Statement stmt, int lineNumber, PrintStream outStream) {
        super(lineNumber, outStream);
        this.id = id;
        this.stmt = stmt;
    }
    
    /*
     * 
     * stmt begin lbl: 
     *      goto lbl2 (func)
     * lbl1: 
     *      goto after func
     * lbl2: 
     *      func stmnts....
     *      goto lb1
     * lbl after statement:
     * 
     */
    @Override
    public void generate(int stmtBeginLabel, int labelAfterStatement) {
        int l1 = newLabel();
        int l2 = newLabel();
        
        emitString("goto L" + l2);
        emitLabel(l1);
        emitString("goto L" + labelAfterStatement);
        emitLabel(l2);
        stmt.generate(l2, l1);
        emitString("goto L" + l1);
    }
}
