#Josue Uriarte Reyes
#joauriar@ucsc.edu
#CS12M lab7
#03/11/19
#------------------------------------------------------------------------------
#   Makefile for Dictionary
#------------------------------------------------------------------------------

# Variables
SOURCES    = Dictionary.java DuplicateKeyException.java KeyNotFoundException.java\
             DictionaryInterface.java DictionaryClient.java
MAINCLASS  = DictionaryClient
CLASSES    = DictionaryClient.class Dictionary.class DictionaryInterface.class\
             DuplicateKeyException.class KeyNotFoundException.class\
             Dictionary\$$Node.class
JARFILE    = DictionaryClient
FILES      = $(SOURCES) README Makefile
SUBMIT	   = submit cmps012b-pt.w19 lab7

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
	ls  /afs/cats.ucsc.edu/class/cmps012b-pt.w19/lab7/joauriar
