/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch.parser;

import com.kkirch.ir.And;
import com.kkirch.ir.ArithmeticExpression;
import com.kkirch.ir.ArrayAccess;
import com.kkirch.ir.ArrayAssignmentStatement;
import com.kkirch.ir.AssignmentStatement;
import com.kkirch.ir.Constant;
import com.kkirch.ir.Expression;
import com.kkirch.ir.Id;
import com.kkirch.ir.IfElseStatement;
import com.kkirch.ir.IfStatement;
import com.kkirch.ir.Not;
import com.kkirch.ir.Or;
import com.kkirch.ir.Relational;
import com.kkirch.ir.Statement;
import com.kkirch.ir.StatementSequence;
import com.kkirch.ir.UnaryExpression;
import com.kkirch.ir.WhileStatement;
import com.kkirch.lexer.Lexer;
import com.kkirch.lexer.Tag;
import com.kkirch.lexer.Token;
import com.kkirch.lexer.Number;
import com.kkirch.lexer.Word;
import com.kkirch.symbols.Array;
import com.kkirch.symbols.SymbolContext;
import com.kkirch.symbols.Type;
import java.io.IOException;
import java.io.PrintStream;

public class CParser extends Parser {

    public CParser(Lexer lexer, PrintStream outStream) throws IOException {
        super(lexer, outStream);
    }

    //block
    public void program() throws IOException {
        Statement s = block();
        int begin = s.newLabel();
        int after = s.newLabel();
        if (s != Statement.NULL) {
            s.emitLabel(begin);
            s.generate(begin, after);
            s.emitLabel(after);
        }
    }

    //'{' declerations statements '}'
    public Statement block() throws IOException {
        match('{');
        SymbolContext savedContext = currentContext;
        currentContext = new SymbolContext(savedContext);
        declerations();
        Statement s = statements();
        match('}');
        currentContext = savedContext;
        return s;
    }

    public void declerations() throws IOException {
        while (lookahead.tag == Tag.BASIC) {
            Type t = type();
            Token token = lookahead;
            match(Tag.ID);
            match(';');
            Id id = new Id(declaredMemoryUsage, (Word) token, t,
                    lexer.getCurrentLine(), outStream);
            currentContext.put(token, id);
            declaredMemoryUsage += t.getSize();
        }
    }

    public Type type() throws IOException {
        Type t = (Type) lookahead;
        match(Tag.BASIC);
        if (lookahead.tag != '[') {
            return t;
        } else {
            return arrayDimension(t);
        }
    }

    public Type arrayDimension(Type t) throws IOException {
        match('[');
        Token token = lookahead;
        match(Tag.NUM);
        match(']');
        if (lookahead.tag == '[') {
            t = arrayDimension(t);
        }
        return new Array(t, ((Number) token).getVal());
    }

    public Statement statements() throws IOException {
        if (lookahead.tag == '}') {
            return Statement.NULL;
        } else {
            return new StatementSequence(statement(), statements(),
                    lexer.getCurrentLine(), outStream);
        }
    }

    public Statement statement() throws IOException {
        Expression e;
        Statement statement, s1, s2, cachedStatement;
        switch (lookahead.tag) {
            case ';':
                readNextToken();
                return Statement.NULL;
            case Tag.IF:
                match(Tag.IF);
                match('(');
                e = bool();
                match(')');
                s1 = statement();
                if (lookahead.tag != Tag.ELSE) {
                    return new IfStatement(e, s1,
                            lexer.getCurrentLine(), outStream);
                } else {
                    match(Tag.ELSE);
                    s2 = statement();
                    return new IfElseStatement(e, s1, s2,
                            lexer.getCurrentLine(), outStream);
                }
            case Tag.WHILE:
                WhileStatement aWhile = new WhileStatement(lexer.getCurrentLine(), outStream);
                match(Tag.WHILE);
                match('(');
                e = bool();
                match(')');
                s1 = statement();
                aWhile.init(e, s1);
                return aWhile;
            case '{':
                return block();
            default:
                return assignment();
        }
    }

    public Statement assignment() throws IOException {
        Statement s;
        Token t = lookahead;
        match(Tag.ID);
        Id id = currentContext.get(t);
        if (id == null) {
            throwError("Illegal usage of undeclared variable "
                    + t.toString(), lexer.getCurrentLine());
        }

        if (lookahead.tag == '=') {
            readNextToken();
            s = new AssignmentStatement(id, bool(),
                    lexer.getCurrentLine(), outStream);
        } else {
            ArrayAccess arrayAccess = offset(id);
            match('=');
            s = new ArrayAssignmentStatement(arrayAccess, bool(),
                    lexer.getCurrentLine(), outStream);
        }
        match(';');
        return s;
    }

    public Expression bool() throws IOException {
        Expression e = join();
        while (lookahead.tag == Tag.OR) {
            Token token = lookahead;
            readNextToken();
            e = new Or(e, join(), token,
                    lexer.getCurrentLine(), outStream);
        }
        return e;
    }

    public Expression join() throws IOException {
        Expression e = equality();
        while (lookahead.tag == Tag.AND) {
            Token token = lookahead;
            readNextToken();
            e = new And(e, equality(), token,
                    lexer.getCurrentLine(), outStream);
        }
        return e;
    }

    public Expression equality() throws IOException {
        Expression e = relational();
        while (lookahead.tag == Tag.EQ
                || lookahead.tag == Tag.NE) {
            Token token = lookahead;
            readNextToken();
            e = new Relational(e, relational(), token,
                    lexer.getCurrentLine(), outStream);
        }
        return e;
    }

    public Expression relational() throws IOException {
        Expression e = expression();
        switch (lookahead.tag) {
            case '<':
            case '>':
            case Tag.LE:
            case Tag.GE:
                Token token = lookahead;
                readNextToken();
                return new Relational(e, expression(), token,
                        lexer.getCurrentLine(), outStream);
            default:
                return e;
        }
    }

    public Expression expression() throws IOException {
        Expression e = term();
        while (lookahead.tag == '+'
                || lookahead.tag == '-') {
            Token token = lookahead;
            readNextToken();
            e = new ArithmeticExpression(e, term(), token,
                    lexer.getCurrentLine(), outStream);
        }
        return e;
    }

    public Expression term() throws IOException {
        Expression e = unary();
        while (lookahead.tag == '*'
                || lookahead.tag == '/') {
            Token token = lookahead;
            readNextToken();
            e = new ArithmeticExpression(e, unary(), token,
                    lexer.getCurrentLine(), outStream);
        }
        return e;
    }

    public Expression unary() throws IOException {
        if (lookahead.tag == '-') {
            readNextToken();
            return new UnaryExpression(unary(), Word.minus,
                    lexer.getCurrentLine(), outStream);
        } else if (lookahead.tag == '!') {
            Token token = lookahead;
            readNextToken();
            return new Not(unary(), token,
                    lexer.getCurrentLine(), outStream);
        } else {
            return factor();
        }
    }

    public Expression factor() throws IOException {
        Expression e;
        switch (lookahead.tag) {
            case '(':
                readNextToken();
                e = bool();
                match(')');
                return e;
            case Tag.NUM:
                e = new Constant(lookahead, Type.INT,
                        lexer.getCurrentLine(), outStream);
                readNextToken();
                return e;
            case Tag.REAL:
                e = new Constant(lookahead, Type.FLOAT,
                        lexer.getCurrentLine(), outStream);
                readNextToken();
                return e;
            case Tag.TRUE:
                e = new Constant(Word.True, Type.BOOL,
                        lexer.getCurrentLine(), outStream);
                readNextToken();
                return e;
            case Tag.FALSE:
                e = new Constant(Word.False, Type.BOOL,
                        lexer.getCurrentLine(), outStream);
                readNextToken();
                return e;
            case Tag.ID:
                String idString = lookahead.toString();
                Id id = currentContext.get(lookahead);
                if (id == null) {
                    throwError("Illegal use of undeclared variable "
                            + idString, lexer.getCurrentLine());
                }
                readNextToken();
                if (lookahead.tag != '[') {
                    return id;
                } else {
                    return offset(id);
                }
            default:
                throwError("Unknown syntax error", lexer.getCurrentLine());
                return null;
        }
    }

    ArrayAccess offset(Id arrayId) throws IOException {
        Expression booleanExpression,
                arrayTypeWidth,
                arrayLocation;
        Type t = arrayId.type;
        match('[');
        booleanExpression = bool();
        match(']');
        t = ((Array) t).getTypeOf();
        arrayTypeWidth = new Constant(t.getSize(),
                lexer.getCurrentLine(), outStream);
        arrayLocation = new ArithmeticExpression(booleanExpression, arrayTypeWidth, new Token('*'),
                lexer.getCurrentLine(), outStream);
        //handle multi dimensional array
        while (lookahead.tag == '[') {
            match('[');
            booleanExpression = bool();
            match(']');
            t = ((Array) t).getTypeOf();
            arrayTypeWidth = new Constant(t.getSize(),
                    lexer.getCurrentLine(), outStream);
            Expression offset = new ArithmeticExpression(booleanExpression, arrayTypeWidth, new Token('*'),
                    lexer.getCurrentLine(), outStream);
            arrayLocation = new ArithmeticExpression(arrayLocation, offset, new Token('+'),
                    lexer.getCurrentLine(), outStream);
        }

        return new ArrayAccess(arrayId, arrayLocation, t,
                lexer.getCurrentLine(), outStream);
    }
}
