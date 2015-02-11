all: compile clean

compile:
	rubber -d specifications.tex

clean:
	rm -rf *.aux *.log *.toc *.out

mrproper: clean
	rm -rf *.pdf
