/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.codegen;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

/**
 *
 * @author kkirch
 */
public interface ICodeGenerator {
    static final Pattern LITERAL_ASSIGN_REGEX = Pattern.compile("\\s+(\\w+)(\\s\\[ \\w+ \\])? = (minus)?\\s?([0-9]*\\.?[0-9]+)$");
    static final Pattern VARIABLE_ASSIGN_REGEX = Pattern.compile("\\s+(\\w+)(\\s\\[ \\w+ \\])? = (\\w+)(\\s\\[ \\w+ \\])?$");
    static final Pattern ARITHMETIC_LITERAL_ASSIGN_REGEX_1 = Pattern.compile("\\s+(\\w+)(\\s\\[ \\w+ \\])? = ([-+]?[0-9]*\\.?[0-9]+) ([-+/*]) (\\w+)(\\s\\[ \\w+ \\])?$");
    static final Pattern ARITHMETIC_LITERAL_ASSIGN_REGEX_2 = Pattern.compile("\\s+(\\w+)(\\s\\[ \\w+ \\])? = (\\w+)(\\s\\[ \\w+ \\])? ([-+/*]) ([-+]?[0-9]*\\.?[0-9]+)$");
    static final Pattern ARITHMETIC_VARIABLE_ASSIGN_REGEX = Pattern.compile("\\s+(\\w+)(\\s\\[ \\w+ \\])? = (\\w+)(\\s\\[ \\w+ \\])? ([-+/*]) (\\w+)(\\s\\[ \\w+ \\])?$");
    static final Pattern GOTO_REGEX = Pattern.compile("^\\s+goto\\s(\\w+)$");
    static final Pattern BRANCH_VAR_LITERAL_CMP_REGEX = Pattern.compile("\\s+iffalse (\\w+)(\\s\\[ \\w+ \\])? (<|>|<=|>=|!=|==) ([-+]?[0-9]*\\.?[0-9]+) goto (L\\d+)$");
    static final Pattern BRANCH_LITERAL_VAR_CMP_REGEX = Pattern.compile("\\s+iffalse ([-+]?[0-9]*\\.?[0-9]+) (<|>|<=|>=|!=|==) (\\w+)(\\s\\[ \\w+ \\])? goto (L\\d+)$");
    static final Pattern BRANCH_VAR_VAR_CMP_REGEX = Pattern.compile("\\s+iffalse (\\w+)(\\s\\[ \\w+ \\])? (<|>|<=|>=|!=|==) (\\w+)(\\s\\[ \\w+ \\])? goto (L\\d+)$");
    static final Pattern BRANCH_BOOL_REGEX = Pattern.compile("\\s+iffalse (\\w+)(\\s\\[ \\w+ \\])? goto (L\\d+)$");
    
    void generateCode(InputStream inStream, PrintStream outStream);
}
