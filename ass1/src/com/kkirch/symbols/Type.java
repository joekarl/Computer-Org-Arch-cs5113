/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.symbols;

import com.kkirch.lexer.Tag;
import com.kkirch.lexer.Word;

/**
 *
 * @author kkirch
 */
public class Type extends Word {

    protected final int size;

    //size being the size in bytes that this type uses
    public Type(String token, int tag, int size) {
        super(token, tag);
        this.size = size;
    }
    public static final Type INT = new Type("int", Tag.BASIC, 4),
            FLOAT = new Type("float", Tag.BASIC, 8),
            CHAR = new Type("char", Tag.BASIC, 1),
            BOOL = new Type("bool", Tag.BASIC, 1);

    public static boolean numeric(Type t) {
        return (t == Type.CHAR || t == Type.INT || t == Type.FLOAT);
    }

    public static Type typeConvert(Type a, Type b) {
        if (!numeric(a) || !numeric(b)) {
            return null;
        } else if (a == Type.FLOAT || b == Type.FLOAT) {
            return Type.FLOAT;
        } else if (a == Type.INT || b == Type.INT) {
            return Type.INT;
        } else {
            return Type.CHAR;
        }
    }

    public int getSize() {
        return size;
    }
}
