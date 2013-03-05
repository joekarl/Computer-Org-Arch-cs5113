#ifndef BNF_LEAF_H_
#define BNF_LEAF_H_ 

#include <stdbool.h>

typedef struct bnf_leaf_struct {
    struct bnf_leaf_struct * left;
    struct bnf_leaf_struct * right;
    char * operator;
    char * value;
} BNF_leaf_node;

BNF_leaf_node * bnf_create(BNF_leaf_node * left,
    BNF_leaf_node * right,
    char * operator, char * value);

void bnf_free(BNF_leaf_node * node);

void print_bnf_tree(const BNF_leaf_node * node);

bool is_factor(const BNF_leaf_node * node);

#endif