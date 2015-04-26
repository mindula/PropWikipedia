/**
 * Created by gus on 24/04/15.
 */
public  class AbstractTest<T extends Test> {




    public static void main(String[] args) {
        A a2 = new A(2);
        swap(a2);
        System.out.print(a2.getA());
    }

    private static void swap(A a){
        a = new A(1);
    }

    private static class A{
        int a;
        A(int a){
            this.a =a;
        }

        public int getA() {
            return a;
        }
    }



}
