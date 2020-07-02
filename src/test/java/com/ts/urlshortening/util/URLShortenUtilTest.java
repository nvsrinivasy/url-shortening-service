package com.ts.urlshortening.util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class URLShortenUtilTest {

    @Test
    public void encode_lessThan62() {
        assertEquals("k", URLShortenUtil.encode(10));
    }

    @Test
    public void encode_moreThan62() {
        assertEquals("bq", URLShortenUtil.encode(78));
    }

    @Test
    public void decode_singleCharacter() {
        assertEquals(11, URLShortenUtil.decode("l"));
    }
}
