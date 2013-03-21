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
public class Statement extends Node {
    
    int labelAfterStatement;

    public Statement(int lineNumber, PrintStream outStream) {
        super(lineNumber, outStream);
    }
    public static Statement NULL = new Statement(-1, null);

    public void generate(int stmtBeginLabel, int labelAfterStatement) {
        //empty for null statement
    }
    
}
