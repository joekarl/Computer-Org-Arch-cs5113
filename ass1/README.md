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


###Intermediate Representation
On compile, the compiler first generates an intermediate representation to make ISA specific assembly easier to generate.
This IR is a basic triple stucture. 
Some examples :

* L1:	a = a + 1			//a = a+1;
* 	b = a				//b = a;
* 	c = 5;				//c = 5;
* L3:	iffalse b < 12 goto L4		//if (b < 12) {
*	b = b + 1			//	b = b + 1;
* 	goto L3				//}
* L4:	c = b				//c = b;
* L2:


* L1:	t1 = 8				//float[] a;
*	a(t1) = 5.5			//a[1] = 5.5
* L2:


###Language Spec

The target language is a simple version of C.
The rules are as follows :

* Programs are defined as a set of declerations and statements wrapped in '{ }'
* Declerations must be made at the top of a block
* There is no assignment allowed at decleration time
* The only loop type supported is the WHILE loop
* There is no break
* There are no function calls (as of right now, didn't have time)
* if/else is supported
* nested if/else is supported
* simple type system (only int/float/char/bool)
* comparisons can only be done between like types


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


###Known issues
There are a few known issues and they are as follows (with some explanation)

* No function decleration or handling (didn't have time)
* No switch case (again didn't have time)
* No break statement (had bigger things to finish rather than deal with this)
* Non-like type comparison bug (theres a bug here which prevents comparison between non-like types)
* Unary operator boolean bug (IR parser doesn't convert this correctly and I haven't tracked it down yet)
* Terrible terrible syntax error reporting (compiler basically tells you what line, but after that you're on your own...)
* Horribly un-optimized load/store code (for simplicity's sake, I literally only use at most 4 registers in the load/store code)

