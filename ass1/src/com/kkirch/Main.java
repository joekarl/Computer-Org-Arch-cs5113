package com.kkirch;

import com.kkirch.codegen.ICodeGenerator;
import com.kkirch.codegen.LoadStoreCodeGenerator;
import com.kkirch.codegen.MemoryMemoryCodeGenerator;
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
            try {
                CompilerType compilerType = CompilerType.valueOf(args[0]);
                compile(compilerType, args);
            } catch (Exception e) {
                invalidArgs();
                throw new RuntimeException(e);
            }
        }

    }

    private static void compile(CompilerType compilerType, final String... args) throws IOException {
        ICodeGenerator codeGenerator = null;
        if (compilerType == CompilerType.LDST) {
            codeGenerator = new LoadStoreCodeGenerator();
        } else if (compilerType == CompilerType.MM) {
            codeGenerator = new MemoryMemoryCodeGenerator();
        } 

        //write ir to filename.ir
        FileInputStream fis = new FileInputStream(new File(args[1]));
        FileOutputStream fos = new FileOutputStream(new File(args[1] + ".ir"));
        Lexer lex = new Lexer(fis);

        CParser parser = new CParser(lex, new PrintStream(fos));
        parser.program();

        fis.close();
        fos.close();

        //read from filename.ir and print to standard out
        fis = new FileInputStream(new File(args[1] + ".ir"));
        codeGenerator.generateCode(fis, System.out);

        fis.close();
    }
}
