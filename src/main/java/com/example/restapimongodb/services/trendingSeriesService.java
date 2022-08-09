package com.example.restapimongodb.services;

import com.example.restapimongodb.models.trendingSeries;
import com.example.restapimongodb.models.trendingSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class trendingSeriesService
{
    @Autowired
    private trendingSeriesRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;


    public List<trendingSeries> getTrendingSeries()
    {
        //validation or calculation or call model
        return repository.findAll();
    }

    public void insertIntoTrendingSeries(trendingSeries trendingSeries)
    {
        repository.insert(trendingSeries);
    }

    public Optional<trendingSeries> getATrendingSeries(String id) throws Exception
    {
        Optional<trendingSeries> trendingSeries=repository.findById(id);
        if(!trendingSeries.isPresent())
        {
            throw new Exception("Series with " + id + "is not found");
        }
        return trendingSeries;
    }
}
