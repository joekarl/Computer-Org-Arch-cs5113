/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.lexer;

/**
 *
 * @author kkirch
 */
public class Token {
    public final int tag;

    public Token(int tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return String.format("%d",tag);
    }
}
