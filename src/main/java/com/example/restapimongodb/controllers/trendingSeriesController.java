package com.example.restapimongodb.controllers;

import com.example.restapimongodb.CustomizedResponse;
import com.example.restapimongodb.models.trendingSeries;
import com.example.restapimongodb.services.trendingSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
@CrossOrigin(origins="http://localhost:3001")
@RestController
public class trendingSeriesController 
{
    @Autowired
    private trendingSeriesService service;

    @GetMapping("/trendingSeries")
    public ResponseEntity getTrendingSeries()
    {
        var customizedResponse= new CustomizedResponse("A list of trending Series", service.getTrendingSeries());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/trendingSeries/{id}")
    public ResponseEntity getATrendingSeries(@PathVariable("id")String id) {
        CustomizedResponse customizedResponse= null;
        try {
            customizedResponse = new CustomizedResponse("Trending Series wih id "+ id , Collections.singletonList(service.getATrendingSeries(id)));
        } catch (Exception e) {

            customizedResponse=new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse,HttpStatus.OK);
    }


    @PostMapping(value = "/trendingSeries", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })

    public ResponseEntity addSeries(@RequestBody trendingSeries trendingSeries)
    {
        service.insertIntoTrendingSeries(trendingSeries);
        return new ResponseEntity(trendingSeries, HttpStatus.OK);

    }
}
