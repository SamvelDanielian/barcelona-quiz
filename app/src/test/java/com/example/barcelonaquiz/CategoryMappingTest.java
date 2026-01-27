package com.example.barcelonaquiz;

import org.junit.Test;
import static org.junit.Assert.*;

public class CategoryMappingTest {

    @Test
    public void constructor_withId_setsCorrectName() {
        Category category = new Category(Category.LA_LIGA);
        assertEquals("La Liga", category.getName());
    }

    @Test
    public void constructor_withName_setsCorrectId() {
        Category category = new Category("History");
        assertEquals(Category.HISTORY, category.getId());
    }

    @Test
    public void setId_updatesName() {
        Category category = new Category();
        category.setId(Category.RECORDS);
        assertEquals("Records", category.getName());
    }

    @Test
    public void setName_updatesId() {
        Category category = new Category();
        category.setName("UEFA Super Cup");
        assertEquals(Category.UEFA_SUPER_CUP, category.getId());
    }
}