package in.sarada.collector.priceanalyzer.config;

import in.sarada.collector.priceanalyzer.batch.ItemBatchProcessor;
import in.sarada.collector.priceanalyzer.batch.ItemBatchWriter;
import in.sarada.collector.priceanalyzer.model.ItemInfo;
import in.sarada.collector.priceanalyzer.repos.ItemInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;

@Configuration
@EnableBatchProcessing
public class FlatFileMarketDataReaderConfig {

    @Autowired
    private MarketConfig marketConfig;

    @Autowired
    private ItemInfoRepository itemInfoRepository;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    private Logger logger = LoggerFactory.getLogger(FlatFileMarketDataReaderConfig.class);

    @Bean
    FlatFileItemReader<ItemInfo> itemReader() {

        FlatFileItemReader<ItemInfo> reader = new FlatFileItemReader<>();

        UrlResource resource = null;
        try {
            resource = new UrlResource(marketConfig.getDataUrl());
            reader.setResource(resource);
            reader.setLinesToSkip(1);
            reader.setLineMapper(new DefaultLineMapper<>() {
                {
                    setLineTokenizer(new DelimitedLineTokenizer() {
                        {
                            setNames("state", "district", "market", "commodity", "variety", "arrival_date", "min_price", "max_price", "modal_price");
                        }
                    });

                    setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {
                        {
                            setTargetType(ItemInfo.class);
                        }
                    });
                }
            });
        } catch (MalformedURLException e) {
            logger.error("URL Malformed", e);
        }

        return reader;
    }

    @Bean
    ItemProcessor<ItemInfo, ItemInfo> itemProcessor() {
        return new ItemBatchProcessor();
    }

    @Bean
    ItemBatchWriter<ItemInfo> itemWriter() {
        return new ItemBatchWriter<>(itemInfoRepository);
    }

    @Bean
    Step itemSaveStepBuilder() {
        return stepBuilderFactory.get("itemSaveStep")
                .<ItemInfo, ItemInfo>chunk(100)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    Job getDataSaveJob() {
        return jobBuilderFactory.get("itemSaveJob")
                .incrementer(new RunIdIncrementer())
                .start(itemSaveStepBuilder())
                .build();

    }

}
