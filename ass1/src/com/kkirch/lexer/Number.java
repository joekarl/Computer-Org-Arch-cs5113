/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.lexer;

/**
 *
 * @author kkirch
 */
public class Number extends Token {

    private final int val;
    
    public Number(int val) {
        super(Tag.NUM);
        this.val = val;
    }
    
    @Override
    public String toString() {
        return String.format("%d", val);
    }

    public int getVal() {
        return val;
    }
}
