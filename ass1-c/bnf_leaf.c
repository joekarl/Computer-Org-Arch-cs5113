#include "bnf_leaf.h"
#include "compiler_common.h"
#include <stdlib.h>

BNF_leaf_node * bnf_create()
{
    BNF_leaf_node * node = (BNF_leaf_node *) calloc(1, sizeof(BNF_leaf_node));
    return node;
}

void bnf_free(BNF_leaf_node * node)
{
    if (node)
    {
        bnf_free(node->left);
        bnf_free(node->right);
        free(node->operator);
        free(node->value);
    }
    free(node);
}

void print_bnf_tree(const BNF_leaf_node * node)
{
    if (!node)
    {
        return;
    }

    if (!is_factor(node))
    {
        print("(");

        if (node->left)
        {
            print_bnf_tree(node->left);
        }

        if (node->operator)
        {
            print(node->operator);
        }

        if (node->right)
        {
            print_bnf_tree(node->right);
        }

        print(")");
    }
    else
    {
        print(node->value);
    }

}

bool is_factor(const BNF_leaf_node * node)
{
    return (!node->left && !node->right);
}
