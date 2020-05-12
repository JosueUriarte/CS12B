#Josue Uriarte Reyes
#joauriar@ucsc.edu
#CS12M lab6
#03/04/19
#------------------------------------------------------------------------------
#   Makefile for List ADT
#------------------------------------------------------------------------------

# Variables
JARFILE    = ListClient
MAINCLASS  = ListClient
SOURCES    = ListInterface.java ListIndexOutOfBoundsException.java List.java \
             ListClient.java
CLASSES    = ListInterface.class ListIndexOutOfBoundsException.class \
             List.class List\$$Node.class ListClient.class
SUBMIT	   = submit cmps012b-pt.w19 lab6
FILES	   = List.java ListTest.java ListInterface.java ListIndexOutOfBoundsException.java \
			 ListClient.java Makefile README

# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm -f Manifest $(CLASSES)
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint $(SOURCES)

# Phony Targets
clean:
	rm -f $(JARFILE)

run:
	java -jar $(JARFILE) > my-out

submit: $(FILES)
	$(SUBMIT) $(FILES)

check:
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/lab6/joauriar

diff:
	diff my-out model-out
