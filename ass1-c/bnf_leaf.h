#ifndef BNF_LEAF_H_
#define BNF_LEAF_H_ 

#include <stdbool.h>

typedef struct bnf_leaf_struct {
    struct bnf_leaf_struct * left;
    struct bnf_leaf_struct * right;
    char * operator;
    char * value;
    char * function;
    char * identifier;
    struct bnf_leaf_struct * assignment_expression;
} BNF_leaf_node;

BNF_leaf_node * bnf_create();

void bnf_free(BNF_leaf_node * node);

void print_bnf_tree(const BNF_leaf_node * node);

bool is_factor(const BNF_leaf_node * node);

bool is_function(const BNF_leaf_node * node);

bool is_assignment(const BNF_leaf_node * node);

#endif