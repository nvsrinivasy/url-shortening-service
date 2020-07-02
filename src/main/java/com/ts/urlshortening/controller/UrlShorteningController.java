package com.ts.urlshortening.controller;

import com.ts.urlshortening.dto.UrlLongRequest;
import com.ts.urlshortening.service.UrlShorteningService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class UrlShorteningController {

       
    @Autowired
    private  UrlShorteningService urlShorteningService;
    
    /*
     * This function helps to convert long url to short url
     * @param
     * @return
     */
    @ApiOperation(value = "Convert shorten url", notes = "Converts long url to short url")
    @PostMapping("shorten")
    public String convertToShortUrl(@RequestBody UrlLongRequest request) {
        return urlShorteningService.convertToShortUrl(request);
    }

    /*
     * This function helps to convert short url to long url
     * @param
     * @return
     */
    @ApiOperation(value = "Redirect url", notes = "Finds original url from short url and redirects")
    @GetMapping(value = "{shortUrl}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
       String url = urlShorteningService.getOriginalUrl(shortUrl);
     //  System.out.println(" url :"+url);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
