#ifndef COMPILER_COMMON_H_
#define COMPILER_COMMON_H_

#include "bnf_leaf.h"
#include <stdbool.h>

void read_char();

void print_error(const char * error);

void abort_compile(const char * error);

void unexpected_token_received(const char * expected_token, const char * unexpected_token);

void match_token(const char c);

bool is_letter(const char c);

bool is_digit(const char c);

char get_identifier();

char get_number();

void print(const char * str);

void println(const char * str);

char * create_string_from_char(const char c);

#endif