package in.sarada.collector.priceanalyzer.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "location_info")
public class LocationInfo {

    private String id;
    private String market;
    private Double lat;
    private Double lng;

    public LocationInfo(String id, String market, Double lat, Double lng) {
        this.id = id;
        this.market = market;
        this.lat = lat;
        this.lng = lng;
    }

    public LocationInfo() {
    }

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "market")
    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @DynamoDBAttribute(attributeName = "lat")
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @DynamoDBAttribute(attributeName = "lng")
    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
