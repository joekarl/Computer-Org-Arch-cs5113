/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.parser;

import com.kkirch.lexer.Lexer;
import com.kkirch.lexer.Token;
import com.kkirch.symbols.SymbolContext;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class Parser {
    protected final Lexer lexer;
    protected final PrintStream outStream;
    protected Token lookahead;
    protected SymbolContext currentContext;
    protected int declaredMemoryUsage;
    
    public Parser(Lexer lexer, PrintStream outStream) throws IOException {
        this.lexer = lexer;
        this.outStream = outStream;
        readNextToken();
    }
    
    protected void readNextToken() throws IOException {
        lookahead = lexer.scan();
    }
    
    protected void throwError(String error, int lineNumber) {
        throw new RuntimeException("Parse error near line " + lineNumber
                + ": " + error);
    }
    
    protected void match(int tag) throws IOException {
        if (lookahead.tag == tag) {
            readNextToken();
        } else {
            throwError("Syntax error", lexer.getCurrentLine());
        }
    }
    
    
}
