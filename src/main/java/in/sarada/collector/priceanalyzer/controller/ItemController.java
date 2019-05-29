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

}
