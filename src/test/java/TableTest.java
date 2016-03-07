import org.junit.Test;

import static org.junit.Assert.*;

public class TableTest {

    @Test
    public void testGetMeals() throws Exception {

    }
    @Test
    public void shouldReturnEmptyTable() throws Exception{
        //given
        Table tableOfMeals = new Table(10);
        Kebab[] nullKebabs = new Kebab[5];
        for(int i=0; i< 5; i++){
            nullKebabs[i] = null;
        }
        // when
        Kebab[] kebabs = tableOfMeals.getMeals(5);
        //then
        assertArrayEquals("Getting meals from empty table should return array of nulls",nullKebabs,kebabs);
    }
    @Test
    public void shouldReturnArrayOfTwoMeals() throws Exception {
        //given
        Table tableOfMeals = new Table(10);

        Kebab[] tmpKebabsOnTable = new Kebab[2];
        for (int i = 0; i < 2; i++) {
            tmpKebabsOnTable[i] = new Kebab(i);
        }
        Kebab[] mealsFromCook = {new Kebab(0), new Kebab(1), new Kebab(2), new Kebab(3), new Kebab(4)};
        tableOfMeals.putMealsOnTable(mealsFromCook);

        // when
        Kebab[] kebabs = tableOfMeals.getMeals(2);


        //then
        for(int i=0; i< tmpKebabsOnTable.length;i++)
        {
            assertEquals("Should be the same elements",kebabs[i].getKebabId(),
                    tmpKebabsOnTable[i].getKebabId());
        }
    }
    @Test
    public void isEnoughEmptySlotsShouldReturnTrue() throws Exception{
        //given
        Table tableOfMeals = new Table(10);
        //when
        boolean is = tableOfMeals.canPutMeals(5);
        //then
        assertTrue("Is enough empty slots",is);
    }
    @Test
    public void isNotEnoughtEmptySlotsShouldReturnFalse() throws Exception{
        //given
        Table tableOfMeals = new Table(4);
        Kebab[] kebabs = {new Kebab(0),new Kebab(1),new Kebab(2),new Kebab(3)};
        tableOfMeals.putMealsOnTable(kebabs);
        //when
        boolean is = tableOfMeals.canPutMeals(4);
        //then
        assertFalse("There's no empty space, Should return false",is);
    }
    @Test
    public void shouldTakelowestIdMeals() throws Exception{
        //given
        Kebab[] shouldBeLikeThisArray = {new Kebab(0),new Kebab(1)};
        Table tableOfMeals = new Table(4);
        Kebab[] kebabs = {new Kebab(3),new Kebab(2),new Kebab(1),new Kebab(0)};
        tableOfMeals.putMealsOnTable(kebabs);
        //when
        Kebab[] kebabFromTable = tableOfMeals.getMeals(2);
        //then
        assertArrayEquals("This arrays should be the same",shouldBeLikeThisArray,kebabFromTable);
    }

    @Test
    public void testPutMealsOnTable() throws Exception {

    }

    @Test
    public void testCountEmptySlotsOnTable() throws Exception {

    }
}