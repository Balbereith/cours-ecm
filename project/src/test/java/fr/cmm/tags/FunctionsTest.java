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
        assertEquals("fn:escapeXml(a)", Functions.newLineTransformnbr("a"));
        assertEquals("fn:escapeXml(a<br>)", Functions.newLineTransformnbr("a/n"));
        assertEquals("fn:escapeXml(a<br>a<br>)", Functions.newLineTransformnbr("a/na/n"));
        assertEquals("fn:escapeXml(&a)", Functions.newLineTransformnbr("&a"));
    }
}