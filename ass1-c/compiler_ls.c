#include "compiler_ls.h"
#include "compiler_common.h"
#include <string.h>

void COMP_LS_Expression()
{
	//read term
	char term = COMP_LS_Term();
	char * mov_str = "MOVE R2, R1";
	println(mov_str);

	char op = current_char();
	char literal[2];
	literal[0] = op;
	literal[1] = '\0';
	switch(op)
	{
		case '+' : 
			COMP_LS_Add();
			break;
		case '-' :
			COMP_LS_Subtract();
			break;
		default : 
			unexpected_token_received("+ or -", literal);
			break;
	}
}

void COMP_LS_Add()
{
	match_token('+');
	COMP_LS_Term();
	println("ADD R3, R1, R2");
}

void COMP_LS_Subtract()
{
	match_token('-');
	COMP_LS_Term();
	println("SUB R3, R1, R2");
}

char COMP_LS_Term()
{
	//get term
	char term = get_number();

	char literal[2];
	literal[0] = term;
	literal[1] = '\0';
	char * part1 = "LOAD R1, ";
	int buffer_size = strlen(part1) + 1;
	char asm_str[buffer_size];
	strcpy(asm_str, part1);
	strcat(asm_str, literal);
	println(asm_str);

	return term;
}