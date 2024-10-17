package csci318.group10.analyticsservice.applicationservice;

import csci318.group10.analyticsservice.shareddomain.events.CartEvent;
import csci318.group10.analyticsservice.shareddomain.events.ProductEvent;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.stereotype.Service;

import java.util.*;

import static csci318.group10.analyticsservice.applicationservice.StreamProcessor.CART_ITEMS_COUNT;
import static csci318.group10.analyticsservice.applicationservice.StreamProcessor.LOW_STOCK_STORE;


@Service
public class InteractiveQuery {

    private final InteractiveQueryService interactiveQueryService;


    public InteractiveQuery(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    public long getNonEmptyCartCount() {
        Set<Integer> uniqueCartIds = new HashSet<>();
        KeyValueIterator<String, Long> all = getTotalCarts().all();

        while (all.hasNext()) {
            KeyValue<String, Long> next = all.next();
            int cartId = Integer.parseInt(next.key);
            uniqueCartIds.add(cartId);
        }

        all.close(); // Don't forget to close the iterator

        return uniqueCartIds.size();
    }

    public List<ProductEvent> getLowStockItems() {
        try {
            ReadOnlyKeyValueStore<String, ProductEvent> keyValueStore = getLowStockStore();
            List<ProductEvent> lowStockItems = new ArrayList<>();
            KeyValueIterator<String, ProductEvent> iterator = keyValueStore.all();
            while (iterator.hasNext()) {
                KeyValue<String, ProductEvent> next = iterator.next();
                lowStockItems.add(next.value);
            }
            iterator.close();
            return lowStockItems;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private ReadOnlyKeyValueStore<String, Long> getTotalCarts() {
        return this.interactiveQueryService.getQueryableStore(CART_ITEMS_COUNT, QueryableStoreTypes.keyValueStore());
    }

    private ReadOnlyKeyValueStore<String, ProductEvent> getLowStockStore() {
        return this.interactiveQueryService.getQueryableStore(LOW_STOCK_STORE, QueryableStoreTypes.keyValueStore());
    }
}
