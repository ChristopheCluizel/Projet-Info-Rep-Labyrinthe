all: compile clean

compile:
	rubber -d ../specifications/specifications.tex
	rubber -d ../conception/conception.tex
	rubber -d rapport.tex
	rm -rf specifications.pdf conception.pdf

clean:
	rm -rf *.aux *.log *.toc *.out

mrproper: clean
	rm -rf *.pdf
