/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.ir;

import com.kkirch.ir.Expression;
import com.kkirch.ir.Temp;
import com.kkirch.lexer.Token;
import com.kkirch.symbols.Type;
import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class Logical extends Expression {

    public final Expression e1, e2;

    public static interface TypeCheck {

        Type validateTypes(Type t1, Type t2);
    }
    public static final TypeCheck BoolTypeCheck = new TypeCheck() {
        public Type validateTypes(Type t1, Type t2) {
            if (t1 != Type.BOOL || t2 != Type.BOOL) {
                return null;
            } else {
                return Type.BOOL;
            }
        }
    };

    public Logical(Expression e1, Expression e2, TypeCheck tCheck,
            Token operator, int lineNumber, PrintStream outStream) {
        super(operator, tCheck.validateTypes(e1.type, e2.type), lineNumber, outStream);
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Expression generate() {
        int l1 = newLabel();
        int l2 = newLabel();
        Temp tempSymbol = new Temp(type, lineNumber, outStream);
        jump(0, l1);
        emitString(tempSymbol + " = true");
        emitString("goto L" + l2);
        emitLabel(l1);
        emitString(tempSymbol + " = false");
        emitLabel(l2);
        return tempSymbol;
    }

    @Override
    public String toString() {
        return e1 + " " + operator + " " + e2;
    }
}
