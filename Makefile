JFLAGS = -g --classpath libs -d ./class -sourcepath ./src 
JC = javac
JVM= java -cp ./class
FILE=
RM= rm -r

.SUFFIXES: .java .class

.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		src/domini/controladors/CtrlAlgorisme.java \
		src/domini/controladors/CtrlCatPag.java \
		src/domini/controladors/CtrlComunitat.java \
		src/domini/controladors/CtrlDibuix.java \
		src/domini/controladors/CtrlWikipedia.java \
		src/domini/controladors/graf/grafgenerator/Criteris/Criteri.java \
		src/domini/controladors/graf/grafgenerator/Criteris/CriteriNomJaroWinkler.java \
		src/domini/controladors/graf/grafgenerator/Criteris/CriteriPaginesComuns.java \
		src/domini/controladors/graf/grafgenerator/Criteris/CriteriSubCategoriesComuns.java \
		src/domini/controladors/graf/grafgenerator/Criteris/CriteriSuperCategoriesComuns.java \
		src/domini/controladors/graf/grafgenerator/GrafGenerator.java \
		src/domini/controladors/graf/OperacionsConjunts.java \
		src/domini/controladors/Historial.java \
		src/domini/JaroWinklerDistance.java \
		src/domini/modeldades/ConjuntComunitatWiki.java \
		src/domini/modeldades/graf/GrafDirigit.java \
		src/domini/modeldades/graf/GrafWikipedia.java \
		src/domini/modeldades/graf/NodeCategoria.java \
		src/domini/modeldades/graf/NodePagina.java \
		src/domini/modeldades/graf/NodeWiki.java \
		src/domini/modeldades/InfoCerca.java \
		src/domini/modeldades/InformacioCjtComunitats.java \
		src/domini/modeldades/TipusAlgorisme.java \
		src/persistencia/CtrlPersistencia.java \
		src/persistencia/GrafParser.java \
		src/presentacio/AlertDialog.java \
		src/presentacio/autocompletat/AutoCompleteComboBoxListener.java \
		src/presentacio/ComparacioExecucions.java \
		src/presentacio/FinestraPrincipal.java \
		src/presentacio/GenerarTemes.java \
		src/presentacio/HistorialVista.java \
		src/presentacio/NavegacioC.java \
		src/presentacio/NavegacioP.java \
		src/presentacio/NavegacioVista.java \
		src/presentacio/OperacioCjtsVista.java \
		src/presentacio/swingold/Carregar.java \
		src/presentacio/swingold/GeneraTemes.java \
		src/presentacio/swingold/ListModelNodeWiki.java \
		src/presentacio/swingold/MainWindow.java \
		src/presentacio/swingold/NavegacioC.java \
		src/presentacio/swingold/NavegacioP.java \
		src/presentacio/swingold/NavegacioVista.java \
		src/presentacio/swingold/OperacionsTemes.java \
		src/presentacio/swingold/Temes.java \
		src/presentacio/TemesVista.java \
		src/prop/classescompartides/algorismes/AlgorismeLouvain.java \
		src/prop/classescompartides/algorismes/BronKerbosch.java \
		src/prop/classescompartides/algorismes/CtrlGirvanBron.java \
		src/prop/classescompartides/algorismes/GirvanNewman.java \
		src/prop/classescompartides/algorismes/grupclique/AlgoritmoClique.java \
		src/prop/classescompartides/algorismes/grupclique/CtrlAlgoritmoClique.java \
		src/prop/classescompartides/algorismes/grupclique/Pair.java \
		src/prop/classescompartides/graf/Algoritme.java \
		src/prop/classescompartides/graf/Arc.java \
		src/prop/classescompartides/graf/Comunitat.java \
		src/prop/classescompartides/graf/ConjuntComunitats.java \
		src/prop/classescompartides/graf/Graf.java \
		src/prop/classescompartides/utils/Pair.java \
		src/tests/TestAlgorismeLouvain.java \
		src/tests/TestComunitatOpConjunts.java \
		src/tests/TestDomain.java \
		src/tests/TestGrafCompartit.java \
		src/tests/TestGrafDirigit.java \
		src/tests/TestGrafGenerator.java \
		src/tests/TestGrafParser.java \
		src/tests/TestGrafWikipedia.java \
		src/tests/TestInfoCerca.java \
		src/tests/TestInformacioCjtComunitats.java \
		src/tests/TestJaroWinkler.java \
		src/tests/TestNodeCategoria.java \
		src/tests/TestNodePagina.java \
		src/tests/TestPersistencia.java \
		src/tests/TestSessio.java \


default: classes


classes: $(CLASSES:.java=.class)



run: 
	$(JVM) presentacio.FinestraPrincipal


clean:
		$(RM) ./class/*
