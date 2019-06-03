package in.sarada.collector.priceanalyzer.services;

import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import in.sarada.collector.priceanalyzer.model.ItemInfo;
import in.sarada.collector.priceanalyzer.repos.ItemInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DataCollectionServiceImpl implements DataCollectionService {

    @Autowired
    private ItemInfoRepository itemInfoRepository;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job dataSaveJob;

    private Logger logger = LoggerFactory.getLogger(DataCollectionServiceImpl.class);

    @Override
    //@Scheduled(fixedRate = 86400000)
    public void initCollection() {

        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        try {
            jobLauncher.run(dataSaveJob, params);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            logger.error("Exception at job execution: ", e);
        }
    }

    @Override
    @Scheduled(fixedDelay = 7 * 24 * 60 * 60 * 1000)
    public void clearData() {
        logger.info("-----Clearing all the data-----");
        try {
            itemInfoRepository.deleteAll();
        } catch (ResourceNotFoundException ex) {
            logger.error("Unable to delete data", ex);
        }
    }

    @Override
    public Iterable<ItemInfo> getAll() {
        return itemInfoRepository.findAll();
    }

    @Override
    public Iterable<ItemInfo> getByStateName(String stateName) {
        return itemInfoRepository.findAllByState(stateName);
    }

    /**
     * Finds all the items in a given state and district combination
     *
     * @param stateName name of state
     * @param district  name of district
     * @return Iterable<ItemInfo>
     */
    @Override
    public Iterable<ItemInfo> findAllByStateAndDistrict(String stateName, String district) {
        return itemInfoRepository.findAllByStateAndDistrict(stateName, district);
    }

    /**
     * Finds all the commodity with given name and in a given state
     *
     * @param stateName name of the state
     * @param commodity name of the commodity
     * @return Iterable<ItemInfo>
     */
    @Override
    public Iterable<ItemInfo> findAllByStateAndCommodity(String stateName, String commodity) {
        return itemInfoRepository.findAllByStateAndCommodity(stateName, commodity);
    }

    /**
     * Finds all the Market level commodity information
     *
     * @param market    name of the market
     * @param commodity name of the commodity
     * @return Iterable<ItemInfo>
     */
    @Override
    public Iterable<ItemInfo> findAllByMarketAndCommodity(String market, String commodity) {
        return itemInfoRepository.findAllByMarketAndCommodity(market, commodity);
    }

    /**
     * Finds all the items with given configuration
     *
     * @param stateName state name
     * @param district  district name
     * @param market    market name
     * @param commodity item name
     * @return Iterable<ItemInfo>
     */
    @Override
    public Iterable<ItemInfo> findAllByStateAndDistrictAndMarketAndCommodity(String stateName, String district, String market, String commodity) {
        return itemInfoRepository.findAllByStateAndDistrictAndMarketAndCommodity(stateName, district, market, commodity);
    }

}
