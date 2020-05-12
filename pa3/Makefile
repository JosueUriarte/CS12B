#Josue Uriarte Reyes
#joauriar@ucsc.edu
#CS12M pa3
#02/18/19
#------------------------------------------------------------------------------
#   Makefile for IntegerList ADT
#------------------------------------------------------------------------------

# Variables
SOURCES    = Dictionary.java DuplicateKeyException.java KeyNotFoundException.java\
             DictionaryInterface.java DictionaryClient.java
MAINCLASS  = DictionaryClient
CLASSES    = DictionaryClient.class Dictionary.class DictionaryInterface.class\
             DuplicateKeyException.class KeyNotFoundException.class\
             Dictionary\$$Node.class
JARFILE    = DictionaryClient
FILES      = $(SOURCES) README Makefile DictionaryTest.java
SUBMIT	   = submit cmps012b-pt.w19 pa3

# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac $(SOURCES)  

clean:
	rm -f $(CLASSES) $(JARFILE)

submit: $(FILES)
	$(SUBMIT) $(FILES)

check:
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/pa3/joauriar
