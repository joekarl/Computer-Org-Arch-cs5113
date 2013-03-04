#include "compiler_ls.h"
#include "compiler_common.h"
#include <string.h>

void COMP_LS_Expression()
{
	//read term
	COMP_LS_Term();

	char op = current_char();
	while(op == '+' || op == '-')
	{
		char * mov_str = "MOVE R2, R1";
		println(mov_str);
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
		println("MOVE R1, R3");
		op = current_char();
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
	println("SUB R3, R2, R1");
}

void COMP_LS_Multiply()
{
	match_token('*');
	COMP_LS_Factor();
	println("MUL R3, R2, R1");
}

void COMP_LS_Divide()
{
	match_token('/');
	COMP_LS_Factor();
	println("DIV R3, R2, R1");
}

void COMP_LS_Factor()
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
}

void COMP_LS_Term()
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
	
	// COMP_LS_Factor();

	// char op = current_char();
	// while(op == '*' || op == '/')
	// {
	// 	println("MOVE R2, R1");
	// 	char literal[2];
	// 	literal[0] = op;
	// 	literal[1] = '\0';
	// 	switch(op)
	// 	{
	// 		case '*' : 
	// 			COMP_LS_Multiply();
	// 			break;
	// 		case '/' :
	// 			COMP_LS_Divide();
	// 			break;
	// 		default : 
	// 			unexpected_token_received("* or /", literal);
	// 			break;
	// 	}
	// 	println("MOVE R1, R3");
	// 	op = current_char();
	// }
}