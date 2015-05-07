all : createDiapo clean

createDiapo:
	rubber -d diapo.tex

clean :
	rm -f *.toc *.nav *.log *.out *.snm *.aux *.bbl *.blg

mrproper: clean
	rm -f diapo.pdf

