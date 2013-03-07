#include <stdio.h>
#include <stdlib.h>
#include "compiler_common.h"
#include "compiler_grammar.h"
#include "bnf_leaf.h"

int main(int argc, char ** argv)
{

	printf("> ");
	read_char();
    skip_white_space();

    BNF_leaf_node * root = COMP_Assignment();

    print_bnf_tree(root);

    bnf_free(root);

    println("");

	return 0;
}