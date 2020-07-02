package com.ts.urlshortening.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.ts.urlshortening.dto.UrlLongRequest;
import com.ts.urlshortening.service.UrlShorteningService;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TinyURLServiceTest {

	 @Autowired
	    private UrlShorteningService urlService;

    @Test
    public void testShortenURL() {
   	for(int i=205;i<209;i++) {
   		System.out.println( " Request id :"+i);
   		UrlLongRequest urlLongRequest = new UrlLongRequest();urlLongRequest.setLongUrl("https://www.oracle.com/java/technologies/"+i);
    	String shorturl  = urlService.convertToShortUrl(urlLongRequest);
    	System.out.println(" shorturl : "+shorturl);
   	}
    }
    
  /*  @Test
    public void testRecoverURL() {
        System.out.println(tinyURLService.recoverURL("1"));
    }*/


}
