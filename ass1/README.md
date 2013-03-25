###Build

Built using ant. 
Ant targets available are:

* dist (default - builds an executable jar)
* compile (builds the class files)
* clean (removes dist and build folders)


###Run

To run, first build using `ant`, then go to the dist folder.
In the dist folder run: 

    `java -jar xxx.jar <compiler type> /path/to/file/to/compile`


###Compiler types

To list the compiler types, use the -h flag
    
    `java -jar xxx.jar -h`


###Compiler Structure

The compiler is broken into 3 parts.

1. The lexer - Takes care of scanning for tokens
2. Syntax parser - parses raw input (using lexer) and outputs an intermediate representation of the parse
3. Architecture specific code generator - parses ir input and outputs ISA specific assembly

If this was a better compiler I'd have a 4th part which would be an optimizer.


###Language Spec

The target language is a simple version of C.
The rules are as follows :

* Declerations must be made at the top of a block
* There is no assignment allowed at decleration time
* The only loop type supported is the WHILE loop
* There is no break
* There are no function calls (as of right now, didn't have time)
* if/else is supported
* nested if/else is supported
* simple type system (only int/float/char/bool)
* comparisons can only be done between like

###Language Grammar

         program -> block
           block -> '{' declerations statements '}'
    declerations -> decleration declerations
     decleration -> type ID
            type -> type '[' NUM ']' | BASIC
      statements -> statement statements
       statement -> assignment '=' bool ';'
                  | IF '(' bool ')' statement
                  | IF '(' bool ')' statement ELSE statement
                  | WHILE '(' bool ')' statement
                  | block 
      assignment -> assignment '[' bool ']'
                  | ID
            bool -> join '||' bool
                  | join
            join -> equality '&&' join
                  | equality
        equality -> relational '==' equality
                  | relational '!=' equality
                  | relational
      relational -> expression '<' expression
                  | expression '>' expression
                  | expression '<=' expression
                  | expression '>=' expression
                  | expression
      expression -> term '+' expression
                  | term '-' expression
                  | term
            term -> unary '*' term 
                  | unary '/' term
                  | unary
           unary -> '!' unary
                  | '-' unary
                  | factor
          factor -> '(' bool ')'
                  | assignment
                  | NUM
                  | REAL
                  | 'true'
                  | 'false'

