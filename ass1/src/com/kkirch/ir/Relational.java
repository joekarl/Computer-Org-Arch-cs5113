/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.ir;

import com.kkirch.ir.Expression;
import com.kkirch.lexer.Token;
import com.kkirch.symbols.Array;
import com.kkirch.symbols.Type;
import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class Relational extends Logical {
    
    public static final TypeCheck RelTypeCheck = new TypeCheck() {
        public Type validateTypes(Type t1, Type t2) {
            if (t1 instanceof Array || t2 instanceof Array) {
                return null;
            } else if (t1 == t2) {
                return Type.BOOL;
            } else {
                return null;
            }
        }
    };

    public Relational(Expression e1, Expression e2, 
            Token operator, int lineNumber, PrintStream outStream) {
        super(e1, e2, RelTypeCheck, operator, lineNumber, outStream);
    }

    @Override
    public void jump(int ifLabel, int elseLabel) {
        Expression a = e1.reduce();
        Expression b = e2.reduce();
        
        String testExpr = a + " " + operator + " " + b;
        emitJump(testExpr, ifLabel, elseLabel);
    }
}
