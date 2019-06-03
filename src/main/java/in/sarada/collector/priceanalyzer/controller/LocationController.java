package in.sarada.collector.priceanalyzer.controller;


import in.sarada.collector.priceanalyzer.model.LocationInfo;
import in.sarada.collector.priceanalyzer.services.GeoLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {


    @Autowired
    private GeoLocationService geoLocationService;

    @GetMapping("")
    public @ResponseBody
    Iterable<LocationInfo> getAllLocations() {
        return geoLocationService.getAllLocations();
    }

    @GetMapping("/market/{name}")
    public @ResponseBody
    Iterable<LocationInfo> getAllLocationsByName(@PathVariable("name") String name) {
        return geoLocationService.getByMarket(name);
    }
}
