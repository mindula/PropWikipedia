package presentacio.javafx.classesVista;

import java.awt.geom.Point2D;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.DefaultVisualizationModel;
import edu.uci.ics.jung.visualization.VisualizationModel;
import graf.GrafParser;
import graf.GrafWikipedia;
import graf.NodeCategoria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.stage.Stage;
import prop.classescompartides.graf.Arc;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/14/15
 */
public class MenuPrincipal implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void carregarDades(ActionEvent event) {
        NavegadorVistes.loadVista(NomsVistes.AfegirDades);
    }

    @FXML
    public void navegarWikipedia(ActionEvent event) {
        NavegadorVistes.loadVista(NomsVistes.NavegacioWikipedia);
    }

    @FXML
    public void mostrarGraf(ActionEvent event) {
        Stage stage = new Stage();
        GrafWikipedia grafWikipedia = new GrafWikipedia();
        try {
            grafWikipedia = GrafParser.parse("misc/cats_small_test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Group root = new Group();
        Scene scene = new Scene(root, 1024, 720, Color.WHITE);

        Group viz1 = new Group();

        List<Arc<NodeCategoria>> arcs = grafWikipedia.getArcs();
        ArrayList<NodeCategoria> nodes = grafWikipedia.getCategories();

        Graph<NodeCategoria, Arc<NodeCategoria>> g = new SparseMultigraph<>();

        for (NodeCategoria node : nodes) g.addVertex(node);
        for (Arc<NodeCategoria> arc : arcs) g.addEdge(arc, arc.getNodeA(), arc.getNodeB());

        Layout circleLayout = new CircleLayout(g);

        VisualizationModel vm1 = new DefaultVisualizationModel(circleLayout, new Dimension(600, 600));

        renderGraph(g, circleLayout, viz1);

        root.getChildren().add(viz1);

        stage.setTitle("Displaying JUNG Graph");
        stage.setScene(scene);
        stage.show();


    }

    private static final int CIRCLE_SIZE = 15;

    private void renderGraph(Graph graph, Layout layout, Group viz) {
        // draw the vertices in the graph
        for (Object v : graph.getVertices()) {
            // Get the position of the vertex
            Point2D p = (Point2D) layout.transform(v);

            // draw the vertex as a circle
            Circle circle = CircleBuilder.create()
                    .centerX(p.getX())
                    .centerY(p.getY())
                    .radius(CIRCLE_SIZE)
                    .build();

            // add it to the group, so it is shown on screen
            viz.getChildren().add(circle);
        }

        // draw the edges
        for (Object n : graph.getEdges()) {
            // get the end points of the edge
            Pair endpoints = graph.getEndpoints(n);

            // Get the end points as Point2D objects so we can use them in the
            // builder
            Point2D pStart = (Point2D) layout.transform(endpoints.getFirst());
            Point2D pEnd = (Point2D) layout.transform(endpoints.getSecond());

            // Draw the line
            Line line = LineBuilder.create()
                    .startX(pStart.getX())
                    .startY(pStart.getY())
                    .endX(pEnd.getX())
                    .endY(pEnd.getY())
                    .build();
            // add the edges to the screen
            viz.getChildren().add(line);
        }
    }

}
