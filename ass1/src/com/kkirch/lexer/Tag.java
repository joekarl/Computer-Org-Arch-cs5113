/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.lexer;

/**
 * Mix of single and non-single character tags 
 * for identifying during parse
 * 
 * @author kkirch
 */
public class Tag {

    public static final int AND = 256,
            EQ = 257,
            INDEX = 258,
            OR = 259,
            BASIC =260,
            FALSE = 261,
            LE = 262,
            REAL = 263,
            BREAK = 264,
            GE = 265,
            MINUS = 266,
            TEMP = 267,
            DO = 268,
            ID = 269,
            NE = 270,
            TRUE = 271,
            ELSE = 272,
            IF = 273,
            NUM = 274,
            WHILE = 275,
            VOID = 276;
}
