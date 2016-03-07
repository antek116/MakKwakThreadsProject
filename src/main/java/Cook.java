/**
 *
 * Class implements instance of cook.
 */
public class Cook implements Runnable {
    private int maksKebabCanProduct;
    private final Table table;
    private int indexOfKebabsMadeToDay;
    private String name;

    /**
     * Construcotr of Cook class.
     * @param Y maximum value of kebab that cook can made.
     * @param table Instance of table where cook puts meals.
     * @param name Name of Cook.
     */
    public Cook(int Y, Table table, String name) {
        this.maksKebabCanProduct = Y;
        this.table = table;
        this.indexOfKebabsMadeToDay = 0;
        this.name = name;
    }

    /**
     * Method simulated work of cook.
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
     * Method made Kebab by cook.
     * @return array of taken kebab by cook.
     */
    public Kebab[] makeKebab() {
        Kebab[] kebabProduced = new Kebab[maksKebabCanProduct];
        for (int i = 0; i < maksKebabCanProduct; i++) {
            kebabProduced[i] = new Kebab(indexOfKebabsMadeToDay++);
        }
        return kebabProduced;
    }

    /**
     * Method where cook waiting for enough space for Kebab,
     * if there's enough empty space he made Kebabs, if not he waiting
     */
    private void service(){
        if (table.canPutMeals(maksKebabCanProduct)) {
            System.out.println("Cook " + name + " : Makes " + maksKebabCanProduct + " Kebab");
            try {
                Thread.sleep(1000);
                table.putMealsOnTable(makeKebab());
                table.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println("Cook " + name + " : There's no space for kebab, Waiting");
                table.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
