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
public class LoadStoreCodeGenerator implements ICodeGenerator {

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
                    if (matcher.group(2) != null) {
                        //array assignment
                        String offset = matcher.group(2).split("\\[")[1].split("\\]")[0].trim();
                        emitString("STORE " + matcher.group(1) + "(" + offset + "), "
                                + "#"
                                + ("minus".equals(matcher.group(3)) ? "-" : "")
                                + matcher.group(4), outStream);
                    } else {
                        emitString("STORE " + matcher.group(1) + ", "
                                + "#"
                                + ("minus".equals(matcher.group(3)) ? "-" : "")
                                + matcher.group(4), outStream);
                    }
                    continue;
                }

                matcher = VARIABLE_ASSIGN_REGEX.matcher(line);
                //literal assignment
                if (matcher.matches()) {
                    String output = "STORE " + matcher.group(1);
                    if (matcher.group(2) != null) {
                        //array assignment
                        String offset = matcher.group(2).split("\\[")[1].split("\\]")[0].trim();
                        output += "(R0), ";
                        emitString("LOAD R0, " + offset, outStream);
                    } else {
                        output += ", ";
                    }


                    if (matcher.group(4) != null) {
                        //array assignment
                        output += matcher.group(3);
                        String offset = matcher.group(4).split("\\[")[1].split("\\]")[0].trim();
                        output += "(R1)";
                        emitString("LOAD R1, " + offset, outStream);
                    } else {
                        emitString("LOAD R1, " + matcher.group(3), outStream);
                        output += "R1";
                    }

                    emitString(output, outStream);

                    continue;
                }


                matcher = ARITHMETIC_LITERAL_ASSIGN_REGEX_1.matcher(line);
                //i = 1 + a;
                if (matcher.matches()) {
                    //a = R0
                    //a(R1)
                    //i(R3)

                    String output = "";
                    String op = matcher.group(4);
                    if ("*".equals(op)) {
                        output += "MULT R0, #" + matcher.group(3) + ", R0";
                    } else if ("/".equals(op)) {
                        output += "DIV R0, #" + matcher.group(3) + ", R0";
                    } else if ("-".equals(op)) {
                        output += "SUB R0, #" + matcher.group(3) + ", R0";
                    } else if ("+".equals(op)) {
                        output += "ADD R0, #" + matcher.group(3) + ", R0";
                    }

                    if (matcher.group(6) != null) {
                        String arrayAccessOutput = "LOAD R0, ";
                        arrayAccessOutput += matcher.group(5);
                        String offset = matcher.group(6).split("\\[")[1].split("\\]")[0].trim();
                        arrayAccessOutput += "(R1)";
                        emitString("LOAD R1, " + offset, outStream);
                        emitString(arrayAccessOutput, outStream);
                    } else {
                        emitString("LOAD R0, " + matcher.group(5), outStream);
                    }

                    emitString(output, outStream);

                    output = "STORE " + matcher.group(1);
                    if (matcher.group(2) != null) {
                        String offset = matcher.group(2).split("\\[")[1].split("\\]")[0].trim();
                        output += "(R3), R0";
                        emitString("LOAD R3, " + offset, outStream);
                    } else {
                        output += ", R0";
                    }
                    emitString(output, outStream);
                    continue;
                }

                matcher = ARITHMETIC_LITERAL_ASSIGN_REGEX_2.matcher(line);
                //i = a + 1;
                if (matcher.matches()) {

                    String output = "";
                    String op = matcher.group(5);
                    if ("*".equals(op)) {
                        output += "MULT R0, R0, #" + matcher.group(6);
                    } else if ("/".equals(op)) {
                        output += "DIV R0, R0, #" + matcher.group(6);
                    } else if ("-".equals(op)) {
                        output += "SUB R0, R0, #" + matcher.group(6);
                    } else if ("+".equals(op)) {
                        output += "ADD R0, R0, #" + matcher.group(6);
                    }

                    if (matcher.group(4) != null) {
                        String arrayAccessOutput = "LOAD R0, ";
                        arrayAccessOutput += matcher.group(3);
                        String offset = matcher.group(4).split("\\[")[1].split("\\]")[0].trim();
                        arrayAccessOutput += "(R1)";
                        emitString("LOAD R1, " + offset, outStream);
                        emitString(arrayAccessOutput, outStream);
                    } else {
                        emitString("LOAD R0, " + matcher.group(3), outStream);
                    }

                    emitString(output, outStream);

                    output = "STORE " + matcher.group(1);
                    if (matcher.group(2) != null) {
                        String offset = matcher.group(2).split("\\[")[1].split("\\]")[0].trim();
                        output += "(R3), R0";
                        emitString("LOAD R3, " + offset, outStream);
                    } else {
                        output += ", R0";
                    }
                    emitString(output, outStream);
                    continue;
                }

                matcher = ARITHMETIC_VARIABLE_ASSIGN_REGEX.matcher(line);
                //a = b + c
                if (matcher.matches()) {
                    //b = R0
                    //c = R1
                    //a(R2)
                    //b(R3)
                    //c(R4)

                    //load b
                    if (matcher.group(4) != null) {
                        String offset = matcher.group(4).split("\\[")[1].split("\\]")[0].trim();
                        emitString("LOAD R3, " + offset, outStream);
                        emitString("LOAD R0, " + matcher.group(3) + "(R3)", outStream);
                    } else {
                        emitString("LOAD R0, " + matcher.group(3), outStream);
                    }

                    //load c
                    if (matcher.group(7) != null) {
                        String offset = matcher.group(7).split("\\[")[1].split("\\]")[0].trim();
                        emitString("LOAD R4, " + offset, outStream);
                        emitString("LOAD R1, " + matcher.group(6) + "(R4)", outStream);
                    } else {
                        emitString("LOAD R1, " + matcher.group(6), outStream);
                    }

                    String output = "";
                    String op = matcher.group(5);
                    if ("*".equals(op)) {
                        output += "MULT R0, R0, R1";
                    } else if ("/".equals(op)) {
                        output += "DIV R0, R0, R1";
                    } else if ("-".equals(op)) {
                        output += "SUB R0, R0, R1";
                    } else if ("+".equals(op)) {
                        output += "ADD R0, R0, R1";
                    }

                    output = "STORE " + matcher.group(1);
                    if (matcher.group(2) != null) {
                        String offset = matcher.group(2).split("\\[")[1].split("\\]")[0].trim();
                        output += "(R2), R0";
                        emitString("LOAD R2, " + offset, outStream);
                    } else {
                        output += ", R0";
                    }
                    emitString(output, outStream);
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
                    //i = R0
                    //i(R1)

                    if (matcher.group(2) != null) {
                        String offset = matcher.group(2).split("\\[")[1].split("\\]")[0].trim();
                        emitString("LOAD R1, " + offset, outStream);
                        emitString("LOAD R0, " + matcher.group(1) + "(R1)", outStream);
                    } else {
                        emitString("LOAD R0, " + matcher.group(1), outStream);
                    }

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

                    output += "R0, #" + matcher.group(4) + ", " + matcher.group(5);
                    emitString(output, outStream);
                    continue;
                }

                matcher = BRANCH_LITERAL_VAR_CMP_REGEX.matcher(line);
                //iffalse 50 < i goto L2
                if (matcher.matches()) {
                    //i = R0
                    //i(R1)

                    if (matcher.group(4) != null) {
                        String offset = matcher.group(4).split("\\[")[1].split("\\]")[0].trim();
                        emitString("LOAD R1, " + offset, outStream);
                        emitString("LOAD R0, " + matcher.group(3) + "(R1)", outStream);
                    } else {
                        emitString("LOAD R0, " + matcher.group(3), outStream);
                    }

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

                    output += "#" + matcher.group(1) + ", R0, " + matcher.group(5);
                    emitString(output, outStream);
                    continue;
                }

                matcher = BRANCH_VAR_VAR_CMP_REGEX.matcher(line);
                //iffalse x < i goto L2
                if (matcher.matches()) {
                    //x = R0
                    //x(R2)
                    //i = R1
                    //i(R3)

                    //load x
                    if (matcher.group(2) != null) {
                        String offset = matcher.group(2).split("\\[")[1].split("\\]")[0].trim();
                        emitString("LOAD R2, " + offset, outStream);
                        emitString("LOAD R0, " + matcher.group(1) + "(R2)", outStream);
                    } else {
                        emitString("LOAD R0, " + matcher.group(1), outStream);
                    }

                    //load i
                    if (matcher.group(5) != null) {
                        String offset = matcher.group(5).split("\\[")[1].split("\\]")[0].trim();
                        emitString("LOAD R3, " + offset, outStream);
                        emitString("LOAD R1, " + matcher.group(4) + "(R3)", outStream);
                    } else {
                        emitString("LOAD R1, " + matcher.group(4), outStream);
                    }

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

                    output += "R0, R1, " + matcher.group(6);
                    emitString(output, outStream);
                    continue;
                }

                matcher = BRANCH_BOOL_REGEX.matcher(line);
                //iffalse boolVal goto L2
                if (matcher.matches()) {
                    if (matcher.group(2) != null) {
                        String offset = matcher.group(2).split("\\[")[1].split("\\]")[0].trim();
                        emitString("LOAD R1, " + offset, outStream);
                        emitString("LOAD R0, " + matcher.group(1) + "(R1)", outStream);
                    } else {
                        emitString("LOAD R0, " + matcher.group(1), outStream);
                    }

                    emitString("BGT R0, #0, " + matcher.group(3), outStream);
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
