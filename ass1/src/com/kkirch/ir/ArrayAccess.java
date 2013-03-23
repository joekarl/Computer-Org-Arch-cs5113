/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.ir;

import com.kkirch.ir.Expression;
import com.kkirch.ir.Id;
import com.kkirch.ir.Operator;
import com.kkirch.lexer.Tag;
import com.kkirch.lexer.Word;
import com.kkirch.symbols.Type;
import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class ArrayAccess extends Operator {
    protected final Id array;
    protected final Expression index;
    
    public ArrayAccess(Id array, Expression index, Type type,
            int lineNumber, PrintStream outStream) {
        super(new Word("[]", Tag.INDEX), type,
                lineNumber, outStream);
        this.array = array;
        this.index = index;
    }

    @Override
    public Expression generate() {
        return new ArrayAccess(array, index.reduce(), 
                type, lineNumber, outStream);
    }

    @Override
    public void jump(int ifLabel, int elseLabel) {
        emitJump(this.reduce().toString(), ifLabel, elseLabel);
    }

    @Override
    public String toString() {
        return array + " [ " + index + " ]";
    }
    
    
}
