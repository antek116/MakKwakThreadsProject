/**
 * Its place where Coock puts Kebab on it
 * and Waiter get Kebab from Table.
 */
public class Table {
    private Kebab[] tableOfMeals;

    /**
     * Constructor of Table.
     * @param N
     */
    Table(int N) {
        tableOfMeals = new Kebab[N];
    }

    public synchronized Kebab[] getMeals(int X) {
        Kebab[] mealsToTake = new Kebab[X];
        for (int i = 0; i < X; i++) {
            mealsToTake[i] = getLowestKebab();
        }
        return mealsToTake;
    }

    public synchronized void putMealsOnTable(Kebab[] kebabList) {
        for (Kebab kebab : kebabList) {
            putOneMealOnTable(kebab);
        }
    }

    public synchronized int countEmptySlotsOnTable() {
        int emptySlots = 0;
        for (Kebab kebab : tableOfMeals) {
            if (kebab == null) {
                emptySlots++;
            }
        }
        return emptySlots;
    }

    private void putOneMealOnTable(Kebab meal) {
        for (int i = 0; i < tableOfMeals.length; i++) {
            if (tableOfMeals[i] == null) {
                tableOfMeals[i] = meal;
                break;
            }
        }
    }

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

    public synchronized boolean canPutMeals(int X) {
        return countEmptySlotsOnTable() >= X;
    }

    public synchronized boolean canTakeMeals(int N) {
        return tableOfMeals.length - countEmptySlotsOnTable() >= N;
    }
}

