package in.sarada.collector.priceanalyzer.services;

import in.sarada.collector.priceanalyzer.model.ItemInfo;

public interface DataCollectionService {

    /**
     * Initiate collection of data.
     */
    void initCollection();

    /**
     * Clear all the data every week.
     */
    void clearData();

    /**
     * Returns all the items
     *
     * @return all the items
     */
    Iterable<ItemInfo> getAll();

    /**
     * Takes state name and gives all the item that are available in that state
     * @param stateName name of the state
     * @return Iterable<ItemInfo>
     */
    Iterable<ItemInfo> getByStateName(String stateName);

    /**
     * Finds all the items in a given state and district combination
     *
     * @param stateName name of state
     * @param district  name of district
     * @return Iterable<ItemInfo>
     */
    Iterable<ItemInfo> findAllByStateAndDistrict(String stateName, String district);

    /**
     * Finds all the commodity with given name and in a given state
     *
     * @param stateName name of the state
     * @param commodity name of the commodity
     * @return Iterable<ItemInfo>
     */
    Iterable<ItemInfo> findAllByStateAndCommodity(String stateName, String commodity);

    /**
     * Finds all the Market level commodity information
     *
     * @param market    name of the market
     * @param commodity name of the commodity
     * @return Iterable<ItemInfo>
     */
    Iterable<ItemInfo> findAllByMarketAndCommodity(String market, String commodity);

    /**
     * Finds all the items with given configuration
     *
     * @param stateName state name
     * @param district  district name
     * @param market    market name
     * @param commodity item name
     * @return Iterable<ItemInfo>
     */
    Iterable<ItemInfo> findAllByStateAndDistrictAndMarketAndCommodity(String stateName, String district, String market, String commodity);
}
