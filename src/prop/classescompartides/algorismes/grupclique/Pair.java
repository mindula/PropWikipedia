package prop.classescompartides.algorismes.grupclique;

import java.util.Objects;

/**
 * Created by Daniel on 21/4/15.
 */

/*Esta clase ha sido creada para poder usar PAIRS. Recordad que en Java no hay pairs implementados*/
public class Pair<L,R> {
    private L l;
    private R r;

    public  Pair(L l, R r){
        this.l = l;
        this.r = r;
    }

    public  Pair(){

    }

    public L getL(){
        return  l;
    }

    public R getR(){
        return r;
    }

    public void setL(L l){
        this.l = l;
    }

    public void setR(R r){
        this.r = r;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        return (Objects.equals(this.l, other.l) && Objects.equals(this.r, other.r)) || (Objects.equals(this.l, other.r) &&
                Objects.equals(this.r,other.l));
    }

    @Override
    public String toString(){
        return this.l.toString() + this.r.toString();
    }
}