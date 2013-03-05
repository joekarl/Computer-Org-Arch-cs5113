#include "compiler_grammar.h"
#include "compiler_common.h"
#include <string.h>
#include <stdlib.h>

BNF_leaf_node * COMP_Expression()
{
	//read term
	BNF_leaf_node * root = bnf_create(COMP_Term(), NULL, NULL, NULL);

	char op = current_char();
	while(op == '+' || op == '-')
	{
		root->operator = create_string_from_char(op);
		switch(op)
		{
			case '+' : 
				root->right = COMP_Add();
				break;
			case '-' :
				root->right = COMP_Subtract();
				break;
			default : 
				unexpected_token_received("+ or -", root->operator);
				break;
		}
		root = bnf_create(root, NULL, NULL, NULL);
		op = current_char();
	}

	return root->right ? root : root->left;
}

BNF_leaf_node * COMP_Add()
{
	match_token('+');
	return COMP_Term();
}

BNF_leaf_node * COMP_Subtract()
{
	match_token('-');
	return COMP_Term();
}

BNF_leaf_node * COMP_Multiply()
{
	match_token('*');
	return COMP_Factor();
}

BNF_leaf_node * COMP_Divide()
{
	match_token('/');
	return COMP_Factor();
}

BNF_leaf_node * COMP_Factor()
{
	BNF_leaf_node * node = NULL;
	if (current_char() == '(')
	{
		match_token('(');
		node = COMP_Expression();
		match_token(')');
	}
	else
	{
		//get term
		char term = get_number();
		char * value = (char *) calloc(2, sizeof(char));
		value[0] = term;

		node = bnf_create(NULL, NULL, NULL, value);
	}
	
	return node;
}

BNF_leaf_node * COMP_Term()
{
	BNF_leaf_node * root = bnf_create(COMP_Factor(), NULL, NULL, NULL);

	char op = current_char();
	while(op == '*' || op == '/')
	{
		root->operator = create_string_from_char(op);
		switch(op)
		{
			case '*' : 
				root->right = COMP_Multiply();
				break;
			case '/' :
				root->right = COMP_Divide();
				break;
			default : 
				unexpected_token_received("* or /", root->operator);
				break;
		}
		root = bnf_create(root, NULL, NULL, NULL);
		op = current_char();
	}


	return root->right ? root : root->left;
}