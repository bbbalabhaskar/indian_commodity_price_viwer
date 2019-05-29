package in.sarada.collector.priceanalyzer.repos;


import in.sarada.collector.priceanalyzer.model.ItemInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
@ComponentScan
public interface ItemInfoRepository extends CrudRepository<ItemInfo, String> {

    @Override
    Iterable<ItemInfo> findAllById(Iterable<String> id);

    @Override
    Iterable<ItemInfo> findAll();

    Iterable<ItemInfo> findAllByState(String sateName);

    Iterable<ItemInfo> findAllByStateAndDistrict(String stateName, String district);

    Iterable<ItemInfo> findAllByStateAndCommodity(String stateName, String commodity);

    Iterable<ItemInfo> findAllByMarketAndCommodity(String market, String commodity);

    Iterable<ItemInfo> findAllByStateAndDistrictAndMarketAndCommodity(String stateName, String district, String market, String commodity);
}
