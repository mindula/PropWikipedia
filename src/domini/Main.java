package domini;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Pagina p = new Pagina("Pagina funciona");
        System.out.println(p.getNom());
        Categoria c = new Categoria("Categoria funciona");
        System.out.println(c.getNom());
    }



}
