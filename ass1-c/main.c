#include <stdio.h>
#include <stdlib.h>
#include "compiler_common.h"
#include "compiler_a.h"

int main(int argc, char ** argv)
{

	printf("> ");
	read_char();
	COMP_A_Expression();

	return 0;
}