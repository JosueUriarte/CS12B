#Josue Uriarte Reyes
#joauriar@ucsc.edu
#CS12M pa5
#03/14/19
#------------------------------------------------------------------------------
# Makefile for DictionaryClient.c with macros
#------------------------------------------------------------------------------

ADT     = Dictionary
EXEBIN  = $(ADT)Client
SOURCES = $(ADT).c $(EXEBIN).c
OBJECTS = $(ADT).o $(EXEBIN).o
HEADERS = $(ADT).h
FILES   = $(EXEBIN).c $(ADT)Test.c $(ADT).c $(ADT).h Makefile README
SUBMIT  = submit cmps012b-pt.w19 pa5

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

submit: $(FILES)
	$(SUBMIT) $(FILES)

check:
	valgrind --leak-check=full $(EXEBIN)

filecheck:
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/pa5/joauriar
