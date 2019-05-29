package in.sarada.collector.priceanalyzer.batch;

import in.sarada.collector.priceanalyzer.model.ItemInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class ItemBatchProcessor implements ItemProcessor<ItemInfo, ItemInfo> {

    private static final Logger logger = LoggerFactory.getLogger(ItemBatchProcessor.class);

    @Override
    public ItemInfo process(ItemInfo item) throws Exception {
        return item;
    }
}
