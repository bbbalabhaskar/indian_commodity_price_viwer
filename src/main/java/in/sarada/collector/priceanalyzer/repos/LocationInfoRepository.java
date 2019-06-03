package in.sarada.collector.priceanalyzer.repos;

import in.sarada.collector.priceanalyzer.model.LocationInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
@ComponentScan
public interface LocationInfoRepository extends CrudRepository<LocationInfo, String> {

    @Override
    Iterable<LocationInfo> findAllById(Iterable<String> id);

    Iterable<LocationInfo> findAllByMarket(String market);

}
