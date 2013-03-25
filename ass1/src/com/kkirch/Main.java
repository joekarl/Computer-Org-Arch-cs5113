package com.kkirch;

import com.kkirch.lexer.Lexer;
import com.kkirch.parser.CParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author kkirch
 */
public class Main {

    public static void invalidArgs() {
        System.out.println("Invalid arguments\nUsage :\n\tjava -jar xxx.jar "
                + "<compiler type> "
                + "<# of registers> <available memory (in bytes)> "
                + "<# of operations>");
    }

    public static void help() {
        System.out.println("Usage :\n\tjava -jar xxx.jar "
                + "<compiler type> "
                + "<# of registers> <available memory (in bytes)> "
                + "<# of operations>");
        System.out.println("\nCompiler Types :");
        for (CompilerType compilerType : CompilerType.values()) {
            System.out.println("\t" + compilerType.toString());
        }
    }

    public static void main(String... args) throws FileNotFoundException, IOException {
        //        if (args.length != 4) {
        //            if (args.length > 0
        //                    && ("-h".equalsIgnoreCase(args[0])
        //                    || "--help".equalsIgnoreCase(args[0]))) {
        //                help();
        //            } else {
        //                invalidArgs();
        //            }
        //        } else {
        //            CompilerType compilerType;
        //            int registerCount;
        //            int memorySize;
        //            int opCount;
        //            try {
        //                compilerType = CompilerType.valueOf(args[0]);
        //                registerCount = Integer.parseInt(args[1]);
        //                memorySize = Integer.parseInt(args[2]);
        //                opCount = Integer.parseInt(args[3]);
        //            } catch (Exception e) {
        //                invalidArgs();
        //            }
        //
        //
        //        }
        FileInputStream fis = new FileInputStream(new File(args[0]));
        Lexer lex = new Lexer(fis);
        
        CParser parser = new CParser(lex, System.out);
        parser.program();
        System.out.println("\n");
        fis.close();
    }
}
