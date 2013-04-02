package com.kkirch;

import com.kkirch.codegen.AccumulatorCodeGenerator;
import com.kkirch.codegen.ICodeGenerator;
import com.kkirch.codegen.LoadStoreCodeGenerator;
import com.kkirch.codegen.MemoryMemoryCodeGenerator;
import com.kkirch.codegen.StackCodeGenerator;
import com.kkirch.lexer.Lexer;
import com.kkirch.parser.CParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author kkirch
 */
public class Main {

    public static void invalidArgs() {
        System.out.println("Invalid arguments\nUsage :\n\tjava -jar xxx.jar "
                + "<compiler type> /path/to/file/to/compile");
    }

    public static void help() {
        System.out.println("Usage :\n\tjava -jar xxx.jar "
                + "<compiler type> /path/to/file/to/compile");
        System.out.println("\nCompiler Types :");
        for (CompilerType compilerType : CompilerType.values()) {
            System.out.println("\t" + compilerType.toString());
        }
    }

    public static void main(final String... args) throws FileNotFoundException, IOException {

        if (args.length != 2) {
            if (args.length > 0
                    && ("-h".equalsIgnoreCase(args[0])
                    || "--help".equalsIgnoreCase(args[0]))) {
                help();
            } else {
                invalidArgs();
            }
        } else {
            CompilerType compilerType;
            try {
                compilerType = CompilerType.valueOf(args[0]);
            } catch (Exception e) {
                invalidArgs();
                throw new RuntimeException(e);
            }
            compile(compilerType, args);
        }

    }

    private static void compile(CompilerType compilerType, final String... args) throws IOException {
        ICodeGenerator codeGenerator = null;
        if (compilerType == CompilerType.LDST) {
            codeGenerator = new LoadStoreCodeGenerator();
        } else if (compilerType == CompilerType.MM) {
            codeGenerator = new MemoryMemoryCodeGenerator();
        } else if (compilerType == CompilerType.ACCUM) {
            codeGenerator = new AccumulatorCodeGenerator();
        } else if (compilerType == CompilerType.STACK) {
            codeGenerator = new StackCodeGenerator();
        } else {
            invalidArgs();
            throw new RuntimeException("Invalid compiler selected");
        }

        //write ir to filename.ir
        FileInputStream fis = new FileInputStream(new File(args[1]));
        FileOutputStream fos = new FileOutputStream(new File(args[1] + ".ir"));
        Lexer lex = new Lexer(fis);
        
        PrintStream ps = new PrintStream(fos);

        CParser parser = new CParser(lex, ps);
        parser.program();

        ps.close();
        fis.close();
        fos.close();

        //read from filename.ir and print to standard out
        fis = new FileInputStream(new File(args[1] + ".ir"));
        
        fos = new FileOutputStream(new File(args[1] + ".asm"));
        ps = new PrintStream(fos);
        codeGenerator.generateCode(fis, ps);

        ps.close();
        fos.close();
        fis.close();
        
        //generate stats
        
    }
}
