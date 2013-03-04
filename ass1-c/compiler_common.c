
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#include "compiler_common.h"

const char TAB_CHAR = '\t';
char g_current_char = '0';

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

bool is_letter(const char c)
{
	return (c >= 'a' && c <= 'Z');
}

bool is_digit(const char c)
{
	return (c >= '0' && c <= '9');
}

char get_identifier()
{
	if (!is_letter(g_current_char))
	{
		char unexpected_token[2];
		unexpected_token[0] = g_current_char;
		unexpected_token[1] = '\0';
		unexpected_token_received("a-zA-Z", unexpected_token);
	}
	else
	{
		char rtn = g_current_char;
		read_char();
		return toupper(rtn);
	}
}

char get_number()
{
	if (!is_digit(g_current_char))
	{
		char unexpected_token[2];
		unexpected_token[0] = g_current_char;
		unexpected_token[1] = '\0';
		unexpected_token_received("0-9", unexpected_token);
	}
	else
	{
		char rtn = g_current_char;
		read_char();
		return toupper(rtn);
	}
}

void print(const char * str)
{
	printf("%c%s", TAB_CHAR, str);
}

void println(const char * str)
{
	print(str);
	printf("\n");
}



