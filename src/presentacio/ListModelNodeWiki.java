package presentacio;

import domini.modeldades.graf.NodeWiki;

import javax.swing.*;
import java.util.ArrayList;


public class ListModelNodeWiki extends AbstractListModel<String> {

    private ArrayList<NodeWiki> nodesWikis;


    @Override
    public int getSize() {
        if(nodesWikis == null)
            return 0;
        else
            return nodesWikis.size();
    }

    @Override
    public String getElementAt(int index) {
        return nodesWikis.get(index).getNom();
    }


    public void setPagines(ArrayList<NodeWiki> nodesWiki) {
        this.nodesWikis = nodesWiki;
    }
}
