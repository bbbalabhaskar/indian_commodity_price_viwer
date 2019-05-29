package in.sarada.collector.priceanalyzer.model;

import java.util.List;


public class DataRecords {

    private String message;
    private Long total;
    private Long count;
    private Long limit;
    private Long offset;
    private List<ItemInfo> records;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public List<ItemInfo> getRecords() {
        return records;
    }

    public void setRecords(List<ItemInfo> records) {
        this.records = records;
    }
}
