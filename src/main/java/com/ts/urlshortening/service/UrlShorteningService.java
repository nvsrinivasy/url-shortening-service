package com.ts.urlshortening.service;

import com.ts.urlshortening.dto.UrlLongRequest;
import com.ts.urlshortening.entity.RequestUrl;
import com.ts.urlshortening.repository.UrlShorteningRepository;
import com.ts.urlshortening.util.URLShortenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class UrlShorteningService {
    
    @Autowired
    private UrlShorteningRepository urlShorteningRepository;

    /*
     * This function helps to convert long url to short url
     * @param UrlLongRequest
     * @return String
     */
    public String convertToShortUrl(UrlLongRequest request) {
    	RequestUrl url = new RequestUrl();
        url.setLongUrl(request.getLongUrl());
        url.setExpiresDate(request.getExpiresDate());
        url.setCreatedDate(new Date());
        RequestUrl entity = urlShorteningRepository.save(url);

        return URLShortenUtil.encode(entity.getId());
    }

    /*
     * This function helps to convert short url to long url
     * @param UrlLongRequest
     * @return String
     */
    public String getOriginalUrl(String shortUrl) {
        long id = URLShortenUtil.decode(shortUrl);
        RequestUrl entity = urlShorteningRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));
        if (entity.getExpiresDate() != null && entity.getExpiresDate().before(new Date())){
        	urlShorteningRepository.delete(entity);
            throw new EntityNotFoundException("Link expired!");
        }
        return entity.getLongUrl();
    }
}
