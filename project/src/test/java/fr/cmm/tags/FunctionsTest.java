package fr.cmm.tags;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * Created by pomme on 17/12/2015.
 */
public class FunctionsTest {

    @Test
    public void testNewLineTransformnbr() throws Exception {
        assertEquals("a", Functions.newLineTransformnbr("a"));
        assertEquals("a<br>", Functions.newLineTransformnbr("a/n"));
        assertEquals("a<br>a<br>", Functions.newLineTransformnbr("a/na/n"));
        assertEquals("&a", Functions.newLineTransformnbr("&a"));
    }
}