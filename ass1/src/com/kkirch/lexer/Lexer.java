/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.lexer;

import com.kkirch.symbols.Type;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kkirch
 */
public class Lexer {

    private int currentLine = 1;
    private char peek = ' ';
    private Map<String, Word> knownWords = new HashMap<String, Word>();
    private final InputStream inStream;

    public Lexer(InputStream inStream) {
        this.inStream = inStream;
        reserveWord(new Word("if", Tag.IF));
        reserveWord(new Word("else", Tag.ELSE));
        reserveWord(new Word("while", Tag.WHILE));
        reserveWord(Word.True);
        reserveWord(Word.False);
        reserveWord(Type.INT);
        reserveWord(Type.FLOAT);
        reserveWord(Type.CHAR);
        reserveWord(Type.BOOL);
    }

    private void reserveWord(Word w) {
        knownWords.put(w.getWord(), w);
    }

    private void readChar() throws IOException {
        peek = (char) inStream.read();
    }
    
    public int getCurrentLine() {
        return currentLine;
    }

    private boolean match(char c) throws IOException {
        readChar();
        if (peek != c) {
            return false;
        } else {
            peek = ' ';
            return true;
        }
    }

    public Token scan() throws IOException {
        //read till we find something thats not whitespace
        while (true) {
            readChar();
            if (' ' == peek || '\t' == peek) {
                continue;
            } else if ('\n' == peek) {
                currentLine++;
                continue;
            } else {
                break;
            }
        }

        //k, now check to see if we hit a known symbol
        switch (peek) {
            case '&':
                if (match('&')) {
                    return Word.and;
                } else {
                    return new Token('&');
                }
            case '|':
                if (match('|')) {
                    return Word.or;
                } else {
                    return new Token('|');
                }
            case '=':
                if (match('=')) {
                    return Word.eq;
                } else {
                    return new Token('=');
                }
            case '!':
                if (match('=')) {
                    return Word.ne;
                } else {
                    return new Token('!');
                }
            case '<':
                if (match('=')) {
                    return Word.le;
                } else {
                    return new Token('<');
                }
            case '>':
                if (match('=')) {
                    return Word.ge;
                } else {
                    return new Token('>');
                }
        }
        
        //check to see if this token is a number
        if (Character.isDigit(peek)) {
            String numString = "";
            do {
                numString += peek;
                readChar();
            } while(Character.isDigit(peek));
            
            //check to see if we're done or we're at a decimal
            if (peek != '.') {
                //its not
                return new Number(Integer.parseInt(numString));
            } else {
                //its a decimal number read decimal parts
                numString += '.';
                readChar();
                while (Character.isDigit(peek)) {
                    numString += peek;
                    readChar();
                }
                
                return new RealNumber(Float.parseFloat(numString));
            }
        }
        
        //so this must be a real token if we hit a character
        if (Character.isLetter(peek)) {
            String tokenString = "";
            do {
                tokenString += peek;
                readChar();
            } while (Character.isLetterOrDigit(peek));
            Word knownWord = knownWords.get(tokenString);
            if (knownWord != null) {
                return knownWord;
            } else {
                knownWord = new Word(tokenString, Tag.ID);
                knownWords.put(tokenString, knownWord);
                return knownWord;
            }
        }
        
        //if we reach here, read individual tokens
        Token t = new Token(peek);
        peek = ' ';
        return t;
    }
}
