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
public class Id extends Expression {

    public final int offsetAddress;

    public Id(int offsetAddress, Word id, Type type, 
            int lineNumber, PrintStream outStream) {
        super(id, type, lineNumber, outStream);
        this.offsetAddress = offsetAddress;
    }
}
