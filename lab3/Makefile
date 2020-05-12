#Josue Uriarte Reyes
#joauriar@ucsc.edu
#CS12M Lab 3
#02/01/19
#––––-------------------------------
# Makefile for FileReverse.c
#––––-------------------------------
FLAGS = -std=c99 -Wall
SOURCES = FileReverse.c
FILES = FileReverse.c README Makefile
OBJECTS = FileReverse.o
EXEBIN = FileReverse
SUBMIT = submit cmps012b-pt.w19 lab3
ASSIGNMENT = lab3

all: $(EXEBIN)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)
	rm $(OBJECTS)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

submit: $(FILES)
	$(SUBMIT) $(FILES)

check:
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/$(ASSIGNMENT)/joauriar
