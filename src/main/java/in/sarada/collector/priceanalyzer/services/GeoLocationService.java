package in.sarada.collector.priceanalyzer.services;

import org.springframework.scheduling.annotation.Scheduled;

public interface GeoLocationService {
    Iterable<in.sarada.collector.priceanalyzer.model.LocationInfo> getAllLocations();

    Iterable<in.sarada.collector.priceanalyzer.model.LocationInfo> getByMarket(String market);

    @Scheduled(fixedRate = 86400000)
    void initLocationCollection();
}
