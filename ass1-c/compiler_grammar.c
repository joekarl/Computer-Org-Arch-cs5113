#include "compiler_grammar.h"
#include "compiler_common.h"
#include <string.h>
#include <stdlib.h>

BNF_leaf_node * COMP_Expression()
{
	char c = current_char();
	BNF_leaf_node * root = NULL;

	if (c == '+' || c == '-')
	{
		BNF_leaf_node * zero_node = bnf_create();
		zero_node->value = create_string_from_char('0');

		read_char(); //advance cursor to next char

		BNF_leaf_node * init_node = bnf_create();
		init_node->left = zero_node;
		init_node->right = COMP_Term();
		init_node->operator = create_string_from_char(c);

		return init_node;
	}
	else
	{
		//read term
		root = bnf_create();
		root->left = COMP_Term();
	}

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
		BNF_leaf_node *temp = root;
		root = bnf_create();
		root->left = temp;
		op = current_char();
	}

	if (root->right)
	{
		return root;
	}
	else
	{
		BNF_leaf_node *rtn = root->left;
		root->left = NULL;
		bnf_free(root);
		return rtn;
	}
}

BNF_leaf_node * COMP_Identifier()
{
	BNF_leaf_node * node = bnf_create();
	char * ident = get_identifier();
	
	//check if function name
	if (current_char() == '(')
	{
		match_token('(');
		match_token(')');
		node->function = ident;
	}
	else
	{
		node->value = ident;
	}
	
	return node;
}

BNF_leaf_node * COMP_Assignment()
{
	BNF_leaf_node * node = bnf_create();
	char * ident = get_identifier();
	match_token('=');
	BNF_leaf_node * expression = COMP_Expression();
	node->identifier = ident;
	node->assignment_expression = expression;
	return node;
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
	char c = current_char();
	BNF_leaf_node * node = NULL;
	if (c == '(')
	{
		match_token('(');
		node = COMP_Expression();
		match_token(')');
	}
	else if (isalpha(c))
	{
		node = COMP_Identifier();
	}
	else
	{
		//get term
		char * term = get_number();

		node = bnf_create();
		node->value = term;
	}
	
	return node;
}

BNF_leaf_node * COMP_Term()
{
	BNF_leaf_node * root = bnf_create();
	root->left = COMP_Factor();

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
		
		BNF_leaf_node *temp = root;
		root = bnf_create();
		root->left = temp;
		op = current_char();
	}

	if (root->right)
	{
		return root;
	}
	else
	{
		BNF_leaf_node *rtn = root->left;
		root->left = NULL;
		bnf_free(root);
		return rtn;
	}
}