#Josue Uriarte Reyes
#joauriar@ucsc.edu
#CS12M Lab4
#02/12/19
#------------------------------------------------------------------------------
# Makefile for Lab 4
#------------------------------------------------------------------------------

PROGRAM_NAME = charType
FLAGS        = -std=c99 -Wall
FILES		 = $(PROGRAM_NAME).c Makefile README
SOURCES      = $(PROGRAM_NAME).c
OBJECTS      = $(PROGRAM_NAME).o
EXEBIN       = $(PROGRAM_NAME)
SUBMIT       = submit cmps012b-pt.w19 lab4

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm $(EXEBIN) $(OBJECTS)

submit: $(FILES)
	$(SUBMIT) $(FILES)

check:
	valgrind --leak-check=full charType infile outfile

filecheck:
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/lab4/joauriar
