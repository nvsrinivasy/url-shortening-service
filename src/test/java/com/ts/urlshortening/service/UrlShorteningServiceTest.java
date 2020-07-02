package com.ts.urlshortening.service;

import com.ts.urlshortening.dto.UrlLongRequest;
import com.ts.urlshortening.entity.RequestUrl;
import com.ts.urlshortening.repository.UrlShorteningRepository;
import com.ts.urlshortening.service.UrlShorteningService;
import com.ts.urlshortening.util.URLShortenUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlShorteningServiceTest {

    @Mock
    UrlShorteningRepository mockUrlShorteningRepository;
    
    @InjectMocks
    UrlShorteningService urlShorteningService;

    /*convertToShortUrlTest()
     * 
     */
    @Test
    public void convertToShortUrlTest() {
    	RequestUrl url = new RequestUrl();
        url.setLongUrl("https://www.oracle.com/java/technologies/javase-downloads.html");
        url.setCreatedDate(new Date());
        url.setId(5);

        when(mockUrlShorteningRepository.save(any(RequestUrl.class))).thenReturn(url);
        when(URLShortenUtil.encode(url.getId())).thenReturn("f");
        UrlLongRequest urlRequest = new UrlLongRequest();
        urlRequest.setLongUrl("https://www.oracle.com/java/technologies/javase-downloads.html");

        assertEquals("f", urlShorteningService.convertToShortUrl(urlRequest));
    }

    /*
     * getOriginalUrlTest()
     */
    @Test
    public void getOriginalUrlTest() {
        when(URLShortenUtil.decode("h")).thenReturn((long) 7);

        RequestUrl url = new RequestUrl();
        url.setLongUrl("https://www.oracle.com/java/technologies/javase-downloads.html");
        url.setCreatedDate(new Date());
        url.setId(7);

        when(mockUrlShorteningRepository.findById((long) 7)).thenReturn(java.util.Optional.of(url));
        assertEquals("https://www.oracle.com/java/technologies/javase-downloads.html", urlShorteningService.getOriginalUrl("h"));

    }
}
