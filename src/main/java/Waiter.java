/**
 *
 *  Created by user on 07.03.2016.
 */
public class Waiter implements Runnable {

    private int maksKebabCanBeTaken;
    private final Table table;
    private String name;

    Waiter(int X, Table table, String name) {
        this.maksKebabCanBeTaken = X;
        this.table = table;
        this.name = name;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        synchronized (table) {
            while (true) {
                service();
            }
        }

    }
    private void service(){
        if (table.canTakeMeals(maksKebabCanBeTaken)) {
            try {
                table.getMeals(maksKebabCanBeTaken);
                System.out.println("Waiter " + name + " : taken " + maksKebabCanBeTaken + " from table. ");
                table.notifyAll();
                table.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println("Waiter " + name + " : There's no kebab avilable, Waiting");
                table.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
