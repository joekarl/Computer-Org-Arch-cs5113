/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.ir;

import com.kkirch.symbols.Type;
import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class AssignmentStatement extends Statement {

    private final Id id;
    private final Expression e;

    public AssignmentStatement(Id id, Expression e,
            int lineNumber, PrintStream outStream) {
        super(lineNumber, outStream);
        this.id = id;
        this.e = e;
        if (!(Type.numeric(id.type) && Type.numeric(e.type))
                && !(id.type == Type.BOOL && e.type == Type.BOOL)) {
            throwError("Cannot assign Type " + e.type.toString()
                    + " to Type " + id.type.toString());
        }
    }

    @Override
    public void generate(int stmtBeginLabel, int labelAfterStatement) {
        emitString(id.toString() + " = " + e.generate());
    }
}
