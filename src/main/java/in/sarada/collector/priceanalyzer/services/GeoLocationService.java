package in.sarada.collector.priceanalyzer.services;

import org.springframework.scheduling.annotation.Scheduled;

public interface GeoLocationService {
    @Scheduled(fixedRate = 86400000)
    void initLocationCollection();
}
