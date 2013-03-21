###Build

Built using ant. 
Ant targets available are:

* dist (builds an executable jar)
* compile (builds the class files)
* clean (removes dist and build folders)


###Run

To run, first build using `ant`, then go to the dist folder.
In the dist folder run: 

    `java -jar Assignment**.jar [options]`

###Language Grammar

         program -> block
           block -> '{' declerations statements '}'
    declerations -> decleration declerations
     decleration -> type ID
            type -> type'[' NUM ']' | BASIC
      statements -> statement statements
       statement -> var '=' bool ';'
                  | IF '(' bool ')' statement
                  | IF '(' bool ')' statement ELSE statement
                  | WHILE '(' bool ')' statement
                  | block 
             var -> var'[' bool ']'
                  | ID
            bool -> join '||' bool
                  | join
            join -> equality '&&' join
                  | equality
        equality -> rel '==' equality
                  | rel '!=' equality
                  | rel
             rel -> expr '<' expr
                  | expr '>' expr
                  | expr '<=' expr
                  | expr '>=' expr
                  | expr
            expr -> term '+' expr
                  | term '-' expr
                  | term
            term -> unary '*' term 
                  | unary '/' term
                  | unary
           unary -> '!' unary
                  | '-' unary
                  | factor
          factor -> '(' bool ')'
                  | var
                  | NUM
                  | REAL
                  | 'true'
                  | 'false'

