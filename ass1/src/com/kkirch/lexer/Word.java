/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.lexer;

/**
 *
 * @author kkirch
 */
public class Word extends Token {
    private final String word;
    
    public Word (String word, int tag) {
        super(tag);
        this.word = word;
    }

    @Override
    public String toString() {
        return word;
    }

    public String getWord() {
        return word;
    }
    
    public static final Word
            and = new Word("&&", Tag.AND),
            or = new Word("||", Tag.OR),
            eq = new Word("==", Tag.EQ),
            ne = new Word("!=", Tag.NE),
            le = new Word("<=", Tag.LE),
            ge = new Word(">=", Tag.GE),
            minus = new Word("minus", Tag.MINUS),
            True = new Word("true", Tag.TRUE),
            False = new Word("false", Tag.FALSE),
            temp = new Word("t", Tag.TEMP),
            Void = new Word("void", Tag.VOID);
    
}
