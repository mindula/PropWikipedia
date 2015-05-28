package presentacio.swingold;

import domini.modeldades.graf.NodeWiki;

import javax.swing.*;
import java.util.ArrayList;


public class ListModelNodeWiki<T extends NodeWiki> extends AbstractListModel<String> {

    private ArrayList<T> nodesWikis;


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


    public void setElements(ArrayList<T> nodesWiki) {

        this.nodesWikis = nodesWiki;
    }

}
