/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.ir;

import com.kkirch.symbols.Array;
import com.kkirch.symbols.Type;
import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class ArrayAssignmentStatement extends Statement {
    private final Id array;
    private final Expression index, e;

    public ArrayAssignmentStatement(ArrayAccess id, Expression e, 
            int lineNumber, PrintStream outStream) {
        super(lineNumber, outStream);
        this.array = id.array;
        this.index = id.index;
        this.e = e;
        
        if ((!(Type.numeric(id.type) && Type.numeric(e.type))
                && !(id.type == e.type))
                || id.type instanceof Array 
                || e.type instanceof Array) {
            throwError("Cannot assign Type " + e.type.toString()
                    + " to Type " + id.type.toString());
        }
    }

    @Override
    public void generate(int stmtBeginLabel, int labelAfterStatement) {
        emitString(array + " [ " + index.reduce() + " ] = " + e.reduce());
    }
}
