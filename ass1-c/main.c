#include <stdio.h>
#include <stdlib.h>
#include "compiler_common.h"
#include "compiler_ls.h"

int main(int argc, char ** argv)
{

	printf("> ");
	read_char();
	COMP_LS_Expression();

	return 0;
}