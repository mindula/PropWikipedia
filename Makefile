JFLAGS = -g -d ./class -sourcepath ./src
JC = javac
JVM= java -cp ./class
FILE=

.SUFFIXES: .java .class

.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		src/org/grupwiki/graf/Arc.java \
		src/org/grupwiki/graf/Graf.java \
		src/org/grupwiki/graf/Algoritme.java \
		src/org/grupwiki/graf/Comunitat.java \
		src/org/grupwiki/graf/ConjuntComunitats.java \
		src/org/grupwiki/louvain/AlgoritmeLouvain.java \
		src/org/grupwiki/louvain/NodeLovain.java \
		src/domini/AlgorismeClique.java \
		src/domini/AlgorismeGirvan.java \
		src/domini/AlgorismeLouvain.java \
		src/domini/Cerca.java \
		src/domini/ComunitatWiki.java \
		src/domini/CtrlAlgorisme.java \
		src/domini/Historial.java \
		src/domini/InfoCerca.java \
		src/domini/InformacioCjtComunitats.java \
		src/domini/LevenshteinDistance.java \
		src/domini/Navegacio.java \
		src/domini/Pair.java \
		src/domini/Sessio.java \
		src/graf/GrafDirigit.java \
		src/graf/grafgenerator/Criteris/Criteri.java \
		src/graf/grafgenerator/Criteris/CriteriNom.java \
		src/graf/grafgenerator/Criteris/CriteriPaginesComuns.java \
		src/graf/grafgenerator/Criteris/CriteriSubCategoriesComuns.java \
		src/graf/grafgenerator/Criteris/CriteriSuperCategoriesComuns.java \
		src/graf/grafgenerator/GrafGenerator.java \
		src/graf/GrafParser.java \
		src/graf/GrafWikipedia.java \
		src/graf/NodeCategoria.java \
		src/graf/NodePagina.java \
		src/graf/NodeWiki.java \
		src/graf/OperacionsConjunts.java \
		src/tests/TestAlgorismeLouvain.java \
		src/tests/TestCercaHistorial.java \
		src/tests/TestComunitatOpConjunts.java \
		src/tests/TestDomain.java \
		src/tests/TestGrafCompartit.java \
		src/tests/TestGrafDirigit.java \
		src/tests/TestGrafGenerator.java \
		src/tests/TestGrafParser.java \
		src/tests/TestGrafWikipedia.java \
		src/tests/TestInfoCerca.java \
		src/tests/TestInformacioCjtComunitats.java \
		src/tests/TestLevenshtein.java \
		src/tests/TestNavegacio.java \
		src/tests/TestNodeCategoria.java \
		src/tests/TestNodePagina.java \
		src/tests/TestSessio.java \



default: classes


classes: $(CLASSES:.java=.class)



run: 
	$(JVM) tests.TestDomain


clean:
		$(RM) class/*.class