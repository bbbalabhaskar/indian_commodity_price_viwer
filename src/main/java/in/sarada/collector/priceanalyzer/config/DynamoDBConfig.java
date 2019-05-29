package in.sarada.collector.priceanalyzer.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import in.sarada.collector.priceanalyzer.model.ItemInfo;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "in.sarada.collector.priceanalyzer.repos")
public class DynamoDBConfig {

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Bean("amazonDynamoDB")
    AmazonDynamoDB dynamoDbLocal() {

        var amazonDynamoDB = AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey)))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, Regions.EU_CENTRAL_1.toString()))
                .build();
        createTableForEntity(amazonDynamoDB, ItemInfo.class);

        return amazonDynamoDB;
    }


    private void createTableForEntity(AmazonDynamoDB amazonDynamoDB, Class<ItemInfo> entity) {

        var tableRequest = new DynamoDBMapper(amazonDynamoDB)
                .generateCreateTableRequest(entity)
                .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

        try {
            new DynamoDB(amazonDynamoDB).createTable(tableRequest).waitForActive();
        } catch (ResourceInUseException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
