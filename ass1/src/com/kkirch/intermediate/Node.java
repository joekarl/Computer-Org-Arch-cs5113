/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.intermediate;

import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class Node {

    private final int lineNumber;
    private static int labelCount = 0;
    private final PrintStream outStream;

    public Node(int lineNumber, PrintStream outStream) {
        this.lineNumber = lineNumber;
        this.outStream = outStream;
    }

    void throwError(String error) {
        throw new RuntimeException("Parse error near line " + lineNumber
                + ": " + error);
    }
    
    public int newLabel() {
        return ++labelCount;
    }
    
    public void emitLabel(int labelNum) {
        outStream.format("L%d:", labelNum);
    }
    
    public void emitString(String s) {
        outStream.format("\t",s);
    }
    
}
