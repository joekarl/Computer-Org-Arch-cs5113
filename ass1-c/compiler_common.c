
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#include "compiler_common.h"

#define MAX_IDENT_SIZE 33 //32 + 1 for null terminator
#define MAX_NUMBER_SIZE 20 //19 + 1 for null terminator

const char TAB_CHAR = '\t';
char g_current_char = '0';


char current_char() 
{
	return g_current_char;
}

void read_char() 
{
	g_current_char = getchar();
}

void print_error(const char * error)
{
	printf("\n");
	printf("Error : %s\n", error);
}

void abort_compile(const char * error)
{
	print_error(error);
	exit(EXIT_FAILURE);
}

void unexpected_token_received(const char * expected_token, const char * unexpected_token) 
{
	//this can buffer overflow, but yeah, not caring at this point....
	char msg[1024];
	strcpy(msg, expected_token);
	strcat(msg, " was expected but ");
	strcat(msg, unexpected_token);
	strcat(msg, " was given");
	abort_compile(msg);
}

void match_token(const char c)
{
	if (g_current_char == c)
	{
		read_char();
		skip_white_space();
	} 
	else
	{
		char expected_token[2];
		expected_token[0] = c;
		expected_token[1] = '\0';

		char unexpected_token[2];
		unexpected_token[0] = g_current_char;
		unexpected_token[1] = '\0';
		unexpected_token_received(expected_token, unexpected_token);
	}
}

char * get_identifier()
{
	if (!isalpha(g_current_char))
	{
		char unexpected_token[2];
		unexpected_token[0] = g_current_char;
		unexpected_token[1] = '\0';
		unexpected_token_received("a-zA-Z", unexpected_token);
	}
	else
	{
		char * rtn = (char *) calloc(MAX_IDENT_SIZE, sizeof(char));
		int i = 0;
		while(isalnum(g_current_char)) {
			rtn[i++] = g_current_char;
			read_char();
			if (i == MAX_IDENT_SIZE && isalnum(g_current_char)) {
				printf("Identifier %s was too large, must be no longer than %d", 
					rtn, MAX_IDENT_SIZE - 1);
				abort_compile("Compilation aborted");
			}
		}
		skip_white_space();
		return rtn;
	}
}

char * get_number()
{
	if (!isdigit(g_current_char))
	{
		char unexpected_token[2];
		unexpected_token[0] = g_current_char;
		unexpected_token[1] = '\0';
		unexpected_token_received("0-9", unexpected_token);
	}
	else
	{
		char * rtn = (char *) calloc(MAX_NUMBER_SIZE, sizeof(char));
		int i = 0;
		while(isdigit(g_current_char)) {
			rtn[i++] = g_current_char;
			read_char();
			if (i == MAX_NUMBER_SIZE && isalnum(g_current_char)) {
				printf("Number %s was too large, must be no longer than %d digits", 
					rtn, MAX_NUMBER_SIZE - 1);
				abort_compile("Compilation aborted");
			}
		}
		skip_white_space();
		return rtn;
	}
}

void print(const char * str)
{
	printf("%s", str);
}

void println(const char * str)
{
	print(str);
	printf("\n");
}

char * create_string_from_char(const char c)
{
	char * string = (char *) calloc(2, sizeof(char));
	string[0] = c;
	return string;
}

void skip_white_space()
{
	while (isblank(g_current_char))
	{
		read_char();
	}
}


