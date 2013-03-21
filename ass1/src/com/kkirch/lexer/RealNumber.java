/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.lexer;

/**
 *
 * @author kkirch
 */
public class RealNumber extends Token {
    private final float f;

    public RealNumber(float f) {
        super(Tag.REAL);
        this.f = f;
    }

    @Override
    public String toString() {
        return String.format("%f", f);
    }
}
