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
    @Scheduled(fixedRate = 86400000)
    public void initCollection() {
        logger.info("-----Clearing all the data-----");
        try{
            itemInfoRepository.deleteAll();
        } catch(ResourceNotFoundException ex) {
            logger.error("Unable to delete data", ex);
        }

        logger.info("-----Dynamo DB data clearance completed/aborted-----");
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
    public Iterable<ItemInfo> getAll() {
        return itemInfoRepository.findAll();
    }

    @Override
    public Iterable<ItemInfo> getByStateName(String stateName) {
        return itemInfoRepository.findAllByState(stateName);
    }

}
