#ifndef COMPILER_A_H_
#define COMPILER_A_H_

#include "compiler_common.h"
#include <string.h>

void COMP_A_Expression()
{
	char * part1 = "MOVE #";
	char * part2 = ", D0";
	char literal[2];
	literal[0] = get_number();
	literal[1] = '\0';
	int buffer_size = strlen(part1) + strlen(part2) + 1;
	char asm_str[buffer_size];
	strcpy(asm_str, part1);
	strcat(asm_str, literal);
	strcat(asm_str, part2);
	println(asm_str);
}

#endif