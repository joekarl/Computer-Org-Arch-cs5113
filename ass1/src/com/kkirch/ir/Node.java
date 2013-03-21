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
public class Node {

    protected final int lineNumber;
    private static int NEXT_LABEL = 0;
    protected final PrintStream outStream;

    public Node(int lineNumber, PrintStream outStream) {
        this.lineNumber = lineNumber;
        this.outStream = outStream;
    }

    void throwError(String error) {
        throw new RuntimeException("Parse error near line " + lineNumber
                + ": " + error);
    }

    public int newLabel() {
        return ++NEXT_LABEL;
    }

    public void emitLabel(int labelNum) {
        outStream.format("L%d:", labelNum);
    }

    public void emitString(String s) {
        outStream.format("\t", s);
    }
}
