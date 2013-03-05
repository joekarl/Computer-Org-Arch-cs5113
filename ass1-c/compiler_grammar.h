#ifndef COMPILER_GRAMMAR_H_
#define COMPILER_GRAMMAR_H_

#include "bnf_leaf.h"

BNF_leaf_node * COMP_Term();
BNF_leaf_node * COMP_Expression();
BNF_leaf_node * COMP_Add();
BNF_leaf_node * COMP_Subtract();
BNF_leaf_node * COMP_Factor();
BNF_leaf_node * COMP_Multiply();
BNF_leaf_node * COMP_Divide();
BNF_leaf_node * COMP_Identifier();
BNF_leaf_node * COMP_Assignment();

#endif