package in.sarada.collector.priceanalyzer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("dsconfig")
public class MarketConfig {

    private String dataUrl;

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getDataUrl() {
        return dataUrl;
    }
}
