/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.symbols;

import com.kkirch.lexer.Tag;

/**
 *
 * @author kkirch
 */
public class Array extends Type {
    private final Type typeOf;
    private final int length;

    public Array(Type typeOf, int length) {
        super("[]", Tag.INDEX, length * typeOf.size);
        this.typeOf = typeOf;
        this.length = length;
    }
    
    @Override
    public String toString() {
        return "[" + length + "] " + typeOf.toString();
    }
}
