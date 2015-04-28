%.pdf: %.md
	pandoc -s -N --smart --bibliography $*.bib -f markdown -o $@ $<

%.beamer.pdf: %.md
	pandoc -s -N --smart --bibliography $*.bib -f markdown -t beamer -o $@ $<

%.html: %.md
	pandoc -s -N --smart --bibliography $*.bib -f markdown -t html --css style.css --standalone -o $@ $<

%.docx: %.md
	pandoc -s --smart --bibliography $*.bib -f markdown -t docx -o $@ $<

%.tex: %.md
	pandoc -s -N --smart --bibliography $*.bib -f markdown -o $@ $<

%.cleaned.md: %.md
	pandoc -f markdown -o $@ $< && mv $@ $<
