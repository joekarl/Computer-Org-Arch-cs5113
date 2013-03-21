/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.ir;

import com.kkirch.lexer.Word;
import com.kkirch.symbols.Type;
import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class Temp extends Expression {
    private static int NEXT_ID = 0;
    private int id;
    public Temp(Type t, int lineNumber, PrintStream outStream) {
        super(Word.temp, t, lineNumber, outStream);
        id = ++NEXT_ID;
    }
}
