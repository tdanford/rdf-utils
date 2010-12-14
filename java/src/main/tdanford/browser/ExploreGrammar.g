grammar ExploreGrammar;

options { 
    output=AST; 
}

tokens { 
	PREFIX = 'prefix' ; 
	CLEAR = 'clear' ; 
	
	FORWARDPROPS = 'f?' ; 
	REVERSEPROPS = 'r?' ;
	FORWARDSTAR = 'f*' ; 
	REVERSESTAR = 'r*' ; 
	 
	FORWARD = 'f' ; 
	REVERSE = 'r' ; 
	
	EVAL = 'g' ; 
	LOOKUP = 'lbl' ; 
	ALL = 'all' ; 
	CHOOSE = 'ch' ; 	
	
	PRETTYPRINT = 'pp' ; 
	LIST = 'l' ; 
	
	DEFINE = 'def' ; 
		
	DOLLAR = '$' ; 
	QUOTE = '"' ; 
	LANGLE = '<' ; 
	RANGLE = '>' ; 
	COLON = ':' ;
	TYPED = '^^' ;
	
	SLASH = '/'; 
	SIZE = '#' ; 
	
	BLANKTYPE   ; 
	TYPEDLITERAL    ; 
}

@header { 
package tdanford.browser;
}

@lexer::header { 
package tdanford.browser;
}

program 
	: WS? command (WS command)* WS? EOF -> command+
	| WS? interpreter_command EOF -> interpreter_command  
	;

command
	: forward_command
	| reverse_command
	| forward_props_command
	| reverse_props_command
	| forward_star_command
	| reverse_star_command
	| eval_command
	| all_command
	| choose_command
	| lookup_command 
	| interpreter_command
	;
	
interpreter_command 
	: list_command 
	| pretty_print_command
	| define_command
	| size_command
	| prefix_command
	| clear_command   
	; 

forward_command : FORWARD WS name 			-> ^(FORWARD name) ;
reverse_command : REVERSE WS name 			-> ^(REVERSE name) ; 
forward_star_command : FORWARDSTAR WS name 	-> ^(FORWARDSTAR name) ;
reverse_star_command : REVERSESTAR WS name 	-> ^(REVERSESTAR name) ; 
forward_props_command : FORWARDPROPS 		-> ^(FORWARDPROPS) ; 
reverse_props_command : REVERSEPROPS 		-> ^(REVERSEPROPS) ; 

lookup_command : LOOKUP WS typed_literal 	-> ^(LOOKUP typed_literal)  ;
eval_command : EVAL WS name 				-> ^(EVAL name) ;
all_command : ALL 							-> ^(ALL) ;
choose_command : CHOOSE 					-> ^(CHOOSE) ;

define_command :  SLASH DEFINE WS VARIABLE 		-> ^(DEFINE VARIABLE) ; 
list_command :  SLASH LIST  					-> ^(LIST) ;
pretty_print_command :  SLASH PRETTYPRINT 		-> ^(PRETTYPRINT) ;
size_command :  SLASH SIZE 						-> ^(SIZE) ;
prefix_command : SLASH PREFIX WS KEYWORD WS IRI -> ^(PREFIX KEYWORD IRI) ;
clear_command : SLASH CLEAR 					-> ^(CLEAR) ;  

custom_command : KEYWORD (WS value)* 			-> ^( KEYWORD value* ) ;

eol : (WS? NEWLINE)+ ;

value 
	: name
	| typed_literal  
	; 

name 
	: resource
	| VARIABLE
	; 

resource 
	: PREFIXED 
	| IRI 
	; 
	
typed_literal 
	: LITERAL  -> ^(TYPEDLITERAL LITERAL BLANKTYPE) 
	| LITERAL TYPED resource -> ^(TYPEDLITERAL LITERAL resource)  
	; 

PREFIXED : KEYWORD COLON KEYWORD ; 

IRI : LANGLE (~ ( RANGLE | NEWLINE ))+ RANGLE ; 

VARIABLE 
	: DOLLAR KEYWORD 
	| DOLLAR INTEGER 
	; 

LITERAL 
	: QUOTED 
	; 

KEYWORD : LETTER (LETTER | DIGIT | '_' | '-' | '.' )* ;

INTEGER : DIGIT+ ;  

fragment QUOTED : QUOTE (~QUOTE)+ QUOTE ; 
fragment DIGIT : '0'..'9' ; 
fragment LETTER : 'a'..'z' | 'A'..'Z' ; 

NEWLINE : '\n' ; 

WS : (' ' | '\u000C' )+ { };

