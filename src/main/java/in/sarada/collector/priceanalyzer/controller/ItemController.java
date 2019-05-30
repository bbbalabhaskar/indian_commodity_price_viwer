package in.sarada.collector.priceanalyzer.controller;

import in.sarada.collector.priceanalyzer.model.ItemInfo;
import in.sarada.collector.priceanalyzer.services.DataCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private DataCollectionService dataCollectionService;

    @GetMapping("")
    public @ResponseBody
    Iterable<ItemInfo> findAll() {
        return dataCollectionService.getAll();
    }

    @GetMapping("/state/{stateName}")
    public @ResponseBody
    Iterable<ItemInfo> findAllByStateName(@PathVariable("stateName") String stateName) {
        return dataCollectionService.getByStateName(stateName);
    }

    @GetMapping("/state/district/{stateName}/{district}")
    public @ResponseBody
    Iterable<ItemInfo> findAllByStateAndDistrict(@PathVariable("stateName") String stateName, @PathVariable("district") String district) {
        return dataCollectionService.findAllByStateAndDistrict(stateName, district);
    }

    @GetMapping("/state/commodity/{stateName}/{commodity}")
    public @ResponseBody
    Iterable<ItemInfo> findAllByStateAndCommodity(@PathVariable("stateName") String stateName, @PathVariable("commodity") String commodity) {
        return dataCollectionService.findAllByStateAndCommodity(stateName, commodity);
    }

    @GetMapping("/market/commodity/{market}/{commodity}")
    public @ResponseBody
    Iterable<ItemInfo> findAllByMarketAndCommodity(@PathVariable("market") String market, @PathVariable("commodity") String commodity) {
        return dataCollectionService.findAllByMarketAndCommodity(market, commodity);
    }

    @GetMapping("/state/distict/market/commodity/{stateName}/{district}/{market}/{commodity}")
    public @ResponseBody
    Iterable<ItemInfo> findAllByStateAndDistrictAndMarketAndCommodity(@PathVariable("stateName") String stateName, @PathVariable("district") String district, @PathVariable("market") String market, @PathVariable("commodity") String commodity) {
        return dataCollectionService.findAllByStateAndDistrictAndMarketAndCommodity(stateName, district, market, commodity);
    }

}
