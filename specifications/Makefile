SRC=$(wildcard *.tex)
OBJ=$(SRC:.tex=.pdf)

all: compile clean

compile: $(OBJ)

%.pdf: %.tex
	pdflatex $^

clean:
	rm -rf *.aux *.log

mrproper:
	make clean
	rm -rf *.pdf
