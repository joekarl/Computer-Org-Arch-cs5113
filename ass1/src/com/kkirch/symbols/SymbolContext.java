/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.symbols;

import com.kkirch.ir.Id;
import com.kkirch.lexer.Token;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kkirch
 */
public class SymbolContext {

    private Map<Token, Id> symbolTable = new HashMap<Token, Id>();
    protected SymbolContext parentContext;

    public SymbolContext(SymbolContext parentContext) {
        this.parentContext = parentContext;
    }

    public void put(Token t, Id i) {
        //System.out.println("Putting token: " + t + " id: " + i + " offset: " + i.offsetAddress);
        symbolTable.put(t, i);
    }

    public Id get(Token t) {
        Id foundId = null;
        SymbolContext currentContext = this;
        while (currentContext != null) {
            foundId = currentContext.symbolTable.get(t);
            if (foundId == null) {
                currentContext = currentContext.parentContext;
            } else {
                break;
            }
        }
//        if (foundId != null) {
//            System.out.println("Getting token: " + t + " found: " + foundId + " offset: " + foundId.offsetAddress);
//        } else {
//            System.out.println("Getting token: " + t + " found: " + foundId);
//        }
        return foundId;
    }

    public void dumpContext(PrintStream outStream) {
        SymbolContext currentContext = this;
        outStream.println("================================");
        outStream.println("Current Context Dump");
        outStream.println("================================");
        while (currentContext != null) {
            for (Map.Entry<Token, Id> entry : currentContext.symbolTable.entrySet()) {
                outStream.println("token: " + entry.getKey() + " id: " + entry.getValue());
            }
            currentContext = currentContext.parentContext;
        }
        outStream.flush();
    }
}
