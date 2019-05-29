package in.sarada.collector.priceanalyzer.batch;

import in.sarada.collector.priceanalyzer.repos.ItemInfoRepository;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
public class ItemBatchWriter<T> implements ItemWriter<in.sarada.collector.priceanalyzer.model.ItemInfo> {

    private final ItemInfoRepository itemInfoRepository;

    public ItemBatchWriter(ItemInfoRepository itemInfoRepository) {
        this.itemInfoRepository = itemInfoRepository;
    }

    public ItemBatchWriter() {
        this.itemInfoRepository = null;
    }

    @Override
    public void write(List<? extends in.sarada.collector.priceanalyzer.model.ItemInfo> items) throws Exception {
        if (itemInfoRepository != null) {
            itemInfoRepository.saveAll(items);
        }
    }
}
