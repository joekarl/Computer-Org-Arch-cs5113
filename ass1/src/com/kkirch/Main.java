package com.kkirch;

import com.kkirch.codegen.ICodeGenerator;
import com.kkirch.codegen.LoadStoreCodeGenerator;
import com.kkirch.lexer.Lexer;
import com.kkirch.parser.CParser;
import java.io.ByteArrayInputStream;
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
//        FileInputStream fis = new FileInputStream(new File(args[0]));
//        Lexer lex = new Lexer(fis);
//        
//        CParser parser = new CParser(lex, System.out);
//        parser.program();
//        System.out.println("\n");
//        fis.close();

        String ir = ""
                + "L1:	i = 0\n"
                + "L3:	i = i + 1\n"
                + "L4:	f = 5.500000\n"
                + "L5:	t1 = i * 8\n"
                + "	a [ t1 ] = f\n"
                + "L6:	iffalse i < 50 goto L2\n"
                + "L7:	i = i + 1\n"
                + "L8:	t2 = i * 8\n"
                + "	t3 = 24 + f\n"
                + "	t4 = 25 * t3\n"
                + "	t5 = i - 1\n"
                + "	t6 = t5 * 8\n"
                + "	t7 = a [ t6 ]\n"
                + "	t8 = t4 + t7\n"
                + "	a [ t2 ] = t8\n"
                + "	goto L6\n"
                + "L2:";

        ByteArrayInputStream is = new ByteArrayInputStream(ir.getBytes());
        ICodeGenerator codeGenerator;
        if (true) {
            codeGenerator = new LoadStoreCodeGenerator();
        }
        
        codeGenerator.generateCode(is, System.out);
        
        is.close();
    }
}
