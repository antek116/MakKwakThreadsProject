/**
 *
 * Created by user on 07.03.2016.
 */
public class Main {

    public static void main(String args[]){
        Table table = new Table(10);
        Thread waiterPatrykThread = new Thread(new Waiter(2,table,"Patryk"));
        Thread waiterFilipThread = new Thread(new Waiter(3,table,"Filip"));
        Thread myCookThread = new Thread(new Cook(7,table, "Krzysztof"));
        waiterFilipThread.start();
        waiterPatrykThread.start();
        myCookThread.start();
    }
}
