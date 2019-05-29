package in.sarada.collector.priceanalyzer.services;

import in.sarada.collector.priceanalyzer.model.ItemInfo;

public interface DataCollectionService {

    void initCollection();

    Iterable<ItemInfo> getAll();

    Iterable<ItemInfo> getByStateName(String stateName);
}
