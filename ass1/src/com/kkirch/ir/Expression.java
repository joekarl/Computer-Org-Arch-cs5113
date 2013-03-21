/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.ir;

import com.kkirch.lexer.Token;
import com.kkirch.symbols.Type;
import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class Expression extends Node {

    protected final Token operator;
    protected final Type type;

    public Expression(Token operator, Type type,
            int lineNumber, PrintStream outStream) {
        super(lineNumber, outStream);
        this.operator = operator;
        this.type = type;
        if (type == null) {
            this.throwError("Invalid type error");
        }
    }

    /*
     * Return right half of intermediate expression
     */
    public Expression generate() {
        return this;
    }

    /*
     * Return an expression that holds the address to this expression
     */
    public Expression reduce() {
        return this;
    }

    /*
     * Do jump
     */
    public void jump(int ifLabel, int elseLabel) {
        emitJump(this.toString(), ifLabel, elseLabel);
    }

    public void emitJump(String testExpr, int ifLabel, int elseLabel) {
        if (ifLabel != 0 && elseLabel != 0) {
            emitString("if " + testExpr + " goto L" + ifLabel);
            emitString("goto L" + elseLabel);
        } else if (ifLabel != 0) {
            emitString("if " + testExpr + " goto L" + ifLabel);
        } else if (elseLabel != 0) {
            emitString("iffalse " + testExpr + " goto L" + elseLabel);
        }
    }

    @Override
    public String toString() {
        return operator.toString();
    }
}
