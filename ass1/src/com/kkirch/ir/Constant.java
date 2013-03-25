/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.ir;

import com.kkirch.lexer.Number;
import com.kkirch.lexer.Token;
import com.kkirch.lexer.Word;
import com.kkirch.symbols.Type;
import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class Constant extends Expression {

    public Constant(Token operator, Type type, 
            int lineNumber, PrintStream outStream) {
        super(operator, type, lineNumber, outStream);
    }
    
    public Constant(int i,
            int lineNumber, PrintStream outStream) {
        super(new Number(i), Type.INT,
                lineNumber, outStream);
    }

    @Override
    public void jump(int ifLabel, int elseLabel) {
        if (this.operator == Word.True 
                && this.type == Type.BOOL
                && ifLabel != 0) {
            emitString("goto L" + ifLabel);
        } else if (this.operator == Word.False 
                && this.type == Type.BOOL
                && ifLabel != 0) {
            emitString("goto L" + elseLabel);
        }
                
    }
    
    
    
}
