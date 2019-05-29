package in.sarada.collector.priceanalyzer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("amazon")
public class AmazonConfig {

    private String endpoint;
    private String accessKey;
    private String secretkey;

    public String getEndpoint() {
        return endpoint;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }
}
