package fr.cmm.helper;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;


public class PaginationTest {

    @Test
    public void getPageCount() {
        Pagination pagination = new Pagination();
        pagination.setCount(50);
        pagination.setPageSize(20);

        assertEquals(3, pagination.getPageCount());
    }

    @Test
    public void getPageCountWhenPageCountIsMultipleOfPageSize() {
        Pagination pagination = new Pagination();
        pagination.setCount(40);
        pagination.setPageSize(20);

        assertEquals(2, pagination.getPageCount());
    }

    @Test
    public void getPages(){
        Pagination pagination = new Pagination();
        pagination.setCount(200);
        pagination.setPageSize(11);
        pagination.setPageIndex(7);

        List<Integer> expectedResult = asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        assertEquals(expectedResult, pagination.getPages());

    }

    @Test
    public void getPagesWhenLessThanTenPages(){
        Pagination pagination = new Pagination();
        pagination.setCount(55);
        pagination.setPageSize(10);
        pagination.setPageIndex(3);

        List<Integer> expectedResult = asList(1,2,3,4,5,6);
        assertEquals(expectedResult, pagination.getPages());
    }

 /*   @Test
    public void getPagesWhenOnRightBorder(){
        Pagination pagination = new Pagination();
        pagination.setCount(20);
        pagination.setPageSize(1);
        pagination.setPageIndex(17);

        List<Integer> expectedResult = asList(11,12,13,14,15,16,17,18,19,20);
        assertEquals(expectedResult, pagination.getPages());
    }
    */
}