/**
 * Its place where Coock puts Kebab on it
 * and Waiter get Kebab from Table.
 */
public class Table {
    private Kebab[] tableOfMeals;

    /**
     * Constructor of Table.
     * @param N max number of kebabs that can be on table.
     */
    Table(int N) {
        tableOfMeals = new Kebab[N];
    }

    /**
     * Method pick up meals from table by Waiter
     * @param X max number of kebabs that waiter can hold.
     * @return array of kebabs that waiter hold.
     */

    public synchronized Kebab[] getMeals(int X) {
        Kebab[] mealsToTake = new Kebab[X];
        for (int i = 0; i < X; i++) {
            mealsToTake[i] = getLowestKebab();
        }
        return mealsToTake;
    }

    /**
     * Method put Kebabs on table
     * @param kebabList all kebabs that cook have made.
     */
    public synchronized void putMealsOnTable(Kebab[] kebabList) {
        for (Kebab kebab : kebabList) {
            putOneMealOnTable(kebab);
        }
    }

    /**
     * Count empty slots on table.
     * @return number of empty space at table.
     */
    public synchronized int countEmptySlotsOnTable() {
        int emptySlots = 0;
        for (Kebab kebab : tableOfMeals) {
            if (kebab == null) {
                emptySlots++;
            }
        }
        return emptySlots;
    }

    /**
     * Method puts one kebab at Table
     * @param meal Kebab made by cook
     */
    private void putOneMealOnTable(Kebab meal) {
        for (int i = 0; i < tableOfMeals.length; i++) {
            if (tableOfMeals[i] == null) {
                tableOfMeals[i] = meal;
                break;
            }
        }
    }

    /**
     * Method to find kebab with lowest id.
     * @return Kebab with lowest id.
     */
    private Kebab getLowestKebab() {
        Kebab kebabToReturn = new Kebab(Integer.MAX_VALUE);
        int indexOfTake = -1;
        for (int i = 0; i < tableOfMeals.length; i++) {
            if (tableOfMeals[i] != null && (tableOfMeals[i].getKebabId() < kebabToReturn.getKebabId())) {
                kebabToReturn = tableOfMeals[i];
                indexOfTake = i;

            }
        }
        tableOfMeals[indexOfTake] = null;
        if (indexOfTake != -1) {
            return kebabToReturn;
        }
        return null;
    }


    /**
     * Methods return that cooker can put meals or not.
     * @param X maximum number of kebab that cook can made.
     * @return true if is enough space or false if not.
     */
    public synchronized boolean canPutMeals(int X) {
        return countEmptySlotsOnTable() >= X;
    }

    /**
     * Method check thats is enough kebabs on table
     * @param N value of kebab that can be pick up by Waiter
     * @return true if is enough Kebab on table, false if not.
     */
    public synchronized boolean canTakeMeals(int N) {
        return tableOfMeals.length - countEmptySlotsOnTable() >= N;
    }
}

