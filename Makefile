JFLAGS = -g -d ./class -sourcepath ./src
JC = javac
JVM= java -cp ./class
FILE=
RM= rm -r

.SUFFIXES: .java .class

.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		src/domini/Cerca.java \
		src/domini/ComunitatWiki.java \
		src/domini/CtrlAlgorisme.java \
		src/domini/Historial.java \
		src/domini/InfoCerca.java \
		src/domini/InformacioCjtComunitats.java \
		src/domini/LevenshteinDistance.java \
		src/domini/Navegacio.java \
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
		src/prop/classescompartides/algorismes/AlgorismeClique.java \
		src/prop/classescompartides/algorismes/AlgorismeGirvan.java \
		src/prop/classescompartides/algorismes/AlgorismeLouvain.java \
		src/prop/classescompartides/graf/Algoritme.java \
		src/prop/classescompartides/graf/Arc.java \
		src/prop/classescompartides/graf/Comunitat.java \
		src/prop/classescompartides/graf/ConjuntComunitats.java \
		src/prop/classescompartides/graf/Graf.java \
		src/prop/classescompartides/utils/Pair.java \
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
		$(RM) ./class/*
