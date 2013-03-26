/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.codegen;

import static com.kkirch.codegen.ICodeGenerator.ARITHMETIC_LITERAL_ASSIGN_REGEX_1;
import static com.kkirch.codegen.ICodeGenerator.BRANCH_LITERAL_VAR_CMP_REGEX;
import static com.kkirch.codegen.ICodeGenerator.BRANCH_VAR_LITERAL_CMP_REGEX;
import static com.kkirch.codegen.ICodeGenerator.LITERAL_ASSIGN_REGEX;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.regex.Matcher;

/**
 *
 * @author kkirch
 */
public class AccumulatorCodeGenerator implements ICodeGenerator {

    public void generateCode(InputStream inStream, PrintStream outStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inStream));

        String line;
        try {
            while ((line = br.readLine()) != null) {
                //does this line have a label?
                if (line.matches("L\\d:.*")) {
                    //it does, print label then print other statements
                    String[] lineSplit = line.split(":");
                    outStream.print(String.format("%s:", lineSplit[0]));
                    if (lineSplit.length > 1) {
                        line = lineSplit[1];
                    } else {
                        continue;
                    }
                }
                //k, we've taken care of the labels, 
                //now lets match our individual instructions

                Matcher matcher = LITERAL_ASSIGN_REGEX.matcher(line);
                //literal assignment
                if (matcher.matches()) {
                    emitString("CLEAR", outStream);
                    emitString("ADD #"
                            + ("minus".equals(matcher.group(3)) ? "-" : "")
                            + matcher.group(4), outStream);

                    if (matcher.group(2) != null) {
                        //array assignment
                        String offset = matcher.group(2).split("\\[")[1].split("\\]")[0].trim();
                        emitString("STORE " + matcher.group(1) + "(" + offset + ")", outStream);
                    } else {
                        emitString("STORE " + matcher.group(1), outStream);
                    }
                    continue;
                }

                matcher = VARIABLE_ASSIGN_REGEX.matcher(line);
                //literal assignment
                if (matcher.matches()) {
                    
                    emitString("CLEAR", outStream);
                    
                    if (matcher.group(4) != null) {
                        //array assignment
                        String offset = matcher.group(4).split("\\[")[1].split("\\]")[0].trim();
                        emitString("ADD " + matcher.group(3) + "(" + offset + ")", outStream);
                    } else {
                        emitString("ADD " + matcher.group(3), outStream);
                    }
                    
                    if (matcher.group(2) != null) {
                        //array assignment
                        String offset = matcher.group(2).split("\\[")[1].split("\\]")[0].trim();
                        emitString("STORE " + matcher.group(1) + "(" + offset + ")", outStream);
                    } else {
                        emitString("STORE " + matcher.group(1), outStream);
                    }
                    
                    continue;
                }


                matcher = ARITHMETIC_LITERAL_ASSIGN_REGEX_1.matcher(line);
                //i = 1 + a;
                if (matcher.matches()) {
                    emitString("CLEAR", outStream);

                    String op = matcher.group(4);
                    if ("*".equals(op)) {
                        op = "MULT ";
                    } else if ("/".equals(op)) {
                        op = "DIV ";
                    } else if ("-".equals(op)) {
                        op = "SUB ";
                    } else if ("+".equals(op)) {
                        op = "ADD ";
                    }

                    emitString("ADD #" + matcher.group(3), outStream);

                    if (matcher.group(6) != null) {
                        String offset = matcher.group(6).split("\\[")[1].split("\\]")[0].trim();
                        emitString(op + matcher.group(5) + "(" + offset + ")", outStream);
                    } else {
                        emitString(op + matcher.group(5), outStream);
                    }

                    if (matcher.group(2) != null) {
                        String offset = matcher.group(2).split("\\[")[1].split("\\]")[0].trim();
                        emitString("STORE " + matcher.group(1) + "(" + offset + ")", outStream);
                    } else {
                        emitString("STORE " + matcher.group(1), outStream);
                    }

                    continue;
                }

                matcher = ARITHMETIC_LITERAL_ASSIGN_REGEX_2.matcher(line);
                //i = a + 1;
                if (matcher.matches()) {

                    emitString("CLEAR", outStream);

                    String op = matcher.group(5);
                    if ("*".equals(op)) {
                        op = "MULT ";
                    } else if ("/".equals(op)) {
                        op = "DIV ";
                    } else if ("-".equals(op)) {
                        op = "SUB ";
                    } else if ("+".equals(op)) {
                        op = "ADD ";
                    }


                    if (matcher.group(4) != null) {
                        String offset = matcher.group(4).split("\\[")[1].split("\\]")[0].trim();
                        emitString("ADD " + matcher.group(3) + "(" + offset + ")", outStream);
                    } else {
                        emitString("ADD " + matcher.group(3), outStream);
                    }

                    emitString(op + "#" + matcher.group(6), outStream);

                    if (matcher.group(2) != null) {
                        String offset = matcher.group(2).split("\\[")[1].split("\\]")[0].trim();
                        emitString("STORE " + matcher.group(1) + "(" + offset + ")", outStream);
                    } else {
                        emitString("STORE " + matcher.group(1), outStream);
                    }

                    continue;
                }

                matcher = ARITHMETIC_VARIABLE_ASSIGN_REGEX.matcher(line);
                //a = b + c
                if (matcher.matches()) {
                    emitString("CLEAR", outStream);

                    String op = matcher.group(5);
                    if ("*".equals(op)) {
                        op = "MULT ";
                    } else if ("/".equals(op)) {
                        op = "DIV ";
                    } else if ("-".equals(op)) {
                        op = "SUB ";
                    } else if ("+".equals(op)) {
                        op = "ADD ";
                    }

                    if (matcher.group(4) != null) {
                        String offset = matcher.group(4).split("\\[")[1].split("\\]")[0].trim();
                        emitString("ADD " + matcher.group(3) + "(" + offset + ")", outStream);
                    } else {
                        emitString("ADD " + matcher.group(3), outStream);
                    }

                    if (matcher.group(7) != null) {
                        String offset = matcher.group(7).split("\\[")[1].split("\\]")[0].trim();
                        emitString(op + matcher.group(6) + "(" + offset + ")", outStream);
                    } else {
                        emitString(op + matcher.group(6), outStream);
                    }

                    if (matcher.group(2) != null) {
                        String offset = matcher.group(2).split("\\[")[1].split("\\]")[0].trim();
                        emitString("STORE " + matcher.group(1) + "(" + offset + ")", outStream);
                    } else {
                        emitString("STORE " + matcher.group(1), outStream);
                    }

                    continue;
                }

                matcher = GOTO_REGEX.matcher(line);
                //goto Lxx
                if (matcher.matches()) {
                    emitString("GOTO " + matcher.group(1), outStream);
                    continue;
                }

                matcher = BRANCH_VAR_LITERAL_CMP_REGEX.matcher(line);
                //iffalse i < 50 goto L2
                if (matcher.matches()) {

                    String output = "";
                    String relational = matcher.group(3);
                    if ("<".equals(relational)) {
                        output += "BLT ";
                    } else if (">".equals(relational)) {
                        output += "BGT ";
                    } else if ("<=".equals(relational)) {
                        output += "BLE ";
                    } else if (">=".equals(relational)) {
                        output += "BGE ";
                    } else if ("==".equals(relational)) {
                        output += "BEQ ";
                    } else if ("!=".equals(relational)) {
                        output += "BNE ";
                    }

                    if (matcher.group(2) != null) {
                        String offset = matcher.group(2).split("\\[")[1].split("\\]")[0].trim();
                        output += matcher.group(1) + "(" + offset + "), ";
                    } else {
                        output += matcher.group(1) + ", ";
                    }

                    output += "#" + matcher.group(4) + ", " + matcher.group(5);
                    emitString(output, outStream);
                    continue;
                }

                matcher = BRANCH_LITERAL_VAR_CMP_REGEX.matcher(line);
                //iffalse 50 < i goto L2
                if (matcher.matches()) {

                    String output = "";
                    String relational = matcher.group(2);
                    if ("<".equals(relational)) {
                        output += "BLT ";
                    } else if (">".equals(relational)) {
                        output += "BGT ";
                    } else if ("<=".equals(relational)) {
                        output += "BLE ";
                    } else if (">=".equals(relational)) {
                        output += "BGE ";
                    } else if ("==".equals(relational)) {
                        output += "BEQ ";
                    } else if ("!=".equals(relational)) {
                        output += "BNE ";
                    }

                    output += "#" + matcher.group(1) + ", ";

                    if (matcher.group(4) != null) {
                        String offset = matcher.group(4).split("\\[")[1].split("\\]")[0].trim();
                        output += matcher.group(3) + "(" + offset + "), ";
                    } else {
                        output += matcher.group(3) + ", ";
                    }

                    output += matcher.group(5);
                    emitString(output, outStream);
                    continue;
                }

                matcher = BRANCH_VAR_VAR_CMP_REGEX.matcher(line);
                //iffalse x < i goto L2
                if (matcher.matches()) {

                    String output = "";
                    String relational = matcher.group(3);
                    if ("<".equals(relational)) {
                        output += "BLT ";
                    } else if (">".equals(relational)) {
                        output += "BGT ";
                    } else if ("<=".equals(relational)) {
                        output += "BLE ";
                    } else if (">=".equals(relational)) {
                        output += "BGE ";
                    } else if ("==".equals(relational)) {
                        output += "BEQ ";
                    } else if ("!=".equals(relational)) {
                        output += "BNE ";
                    }


                    if (matcher.group(2) != null) {
                        String offset = matcher.group(2).split("\\[")[1].split("\\]")[0].trim();
                        output += matcher.group(1) + "(" + offset + "), ";
                    } else {
                        output += matcher.group(1) + ", ";
                    }

                    if (matcher.group(5) != null) {
                        String offset = matcher.group(5).split("\\[")[1].split("\\]")[0].trim();
                        output += matcher.group(4) + "(" + offset + "), ";
                    } else {
                        output += matcher.group(4) + ", ";
                    }

                    output += matcher.group(6);
                    emitString(output, outStream);
                    continue;
                }

                //if we made it here, just print the line...
                emitString("//*****" + line.trim(), outStream);
            }
            emitString("", outStream);
            br.close();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void emitString(String s, PrintStream outStream) {
        outStream.println(String.format("\t%s", s));
    }
}
