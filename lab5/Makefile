#Josue Uriarte Reyes
#joauriar@ucsc.edu
#CS12M Lab 5
#02/24/19
#------------------------------------------------------------------------------
# Makefile for Lab 5
#------------------------------------------------------------------------------

ADT_NAME  = Dictionary
SOURCES   = $(ADT_NAME).c $(ADT_NAME)Client.c
OBJECTS   = $(ADT_NAME).o $(ADT_NAME)Client.o
HEADERS   = $(ADT_NAME).h
EXEBIN    = $(ADT_NAME)Client
TSTSOURCE =	$(ADT_NAME).c $(ADT_NAME)Test.c
TSTOBJ	  = $(ADT_NAME).o $(ADT_NAME)Test.o
TSTHEAD	  =	$(ADT_NAME).h
TSTBIN	  =	$(ADT_NAME)Test
FLAGS     = -std=c99 -Wall
SUBMIT	  = submit cmps012b-pt.w19 lab5
FILES	  = $(ADT_NAME).c $(ADT_NAME)Client.c $(ADT_NAME)Test.c $(ADT_NAME).h Makefile README

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS) $(TSTBIN) $(TSTOBJ)

test: $(TSTBIN)

$(TSTBIN) : $(TSTOBJ) $(TSTHEAD)
	gcc -o $(TSTBIN) $(TSTOBJ)

$(TSTOBJ) : $(TSTSOURCE) $(TSTHEAD)
	gcc -c $(FLAGS) $(TSTSOURCE)

testclean:
	rm -f $(TSTBIN) $(TSTOBJ)

submit: $(FILES)
	$(SUBMIT) $(FILES)

check:
	valgrind --leak-check=full $(EXEBIN) 

filecheck:
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/lab5/joauriar
