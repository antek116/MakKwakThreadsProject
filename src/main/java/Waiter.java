/**
 *
 *  Class implement Waiter.
 */
public class Waiter implements Runnable {

    private int maksKebabCanBeTaken;
    private final Table table;
    private String name;

    /**
     * Constructor of Waiter class.
     * @param X max number of kebab that waiter can hold.
     * @param table instance of table where waiter pick up kebabs from.
     * @param name name of waiter.
     */
    Waiter(int X, Table table, String name) {
        this.maksKebabCanBeTaken = X;
        this.table = table;
        this.name = name;
    }

    /**
     * Method simulated work of waiter.
     */
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        synchronized (table) {
            while (true) {
                service();
            }
        }

    }

    /**
     * Method where waiter waiting for Kebabs taken by Cook.
     * if There's enough kebab he pick up them, if not he waiting.
     */

    private void service(){
        if (table.canTakeMeals(maksKebabCanBeTaken)) {
                table.getMeals(maksKebabCanBeTaken);
                System.out.println("Waiter " + name + " : taken " + maksKebabCanBeTaken + " from table. ");
                table.notifyAll();
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
