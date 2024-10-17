package csci318.group10.analyticsservice.applicationservice;

import csci318.group10.analyticsservice.shareddomain.events.CartEvent;
import csci318.group10.analyticsservice.shareddomain.events.ProductEvent;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;


import java.util.function.Consumer;
import java.util.function.Function;


@Configuration
@EnableKafkaStreams
public class StreamProcessor {
    static final String CART_ITEMS_COUNT = "cartItemsCount";
    static final String LOW_STOCK_STORE = "lowStockStore";

    @Autowired
    private StreamsBuilder streamsBuilder;

    @Bean
    public KStream<String, CartEvent> processCartEvents() {
        KStream<String, CartEvent> stream = streamsBuilder.stream("cart-events", Consumed.with(Serdes.String(), new JsonSerde<>(CartEvent.class)));

        stream
                .filter((key, value) -> value != null && value.getItems() != null && !value.getItems().isEmpty())
                .groupByKey()
                .aggregate(
                        () -> 0L,
                        (key, value, aggregate) -> (long) value.getItems().size(),
                        Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as(CART_ITEMS_COUNT)
                                .withKeySerde(Serdes.String())
                                .withValueSerde(Serdes.Long())
                );

        return stream;
    }

    @Bean
    public KStream<String, ProductEvent> processProductEvents() {
        KStream<String, ProductEvent> stream = streamsBuilder.stream("product-events", Consumed.with(Serdes.String(), new JsonSerde<>(ProductEvent.class)));

        stream
                .filter((key, value) -> value.getQuantity() < 5)
                .groupByKey()
                .reduce((oldValue, newValue) -> newValue,
                        Materialized.<String, ProductEvent, KeyValueStore<Bytes, byte[]>>as(LOW_STOCK_STORE)
                                .withKeySerde(Serdes.String())
                                .withValueSerde(new JsonSerde<>(ProductEvent.class))
                );

        return stream;
    }

    @Bean
    public StoreBuilder<?> cartItemsCountStore() {
        return Stores.keyValueStoreBuilder(
                Stores.persistentKeyValueStore(CART_ITEMS_COUNT),
                Serdes.String(),
                Serdes.Long()
        );
    }

    @Bean
    public StoreBuilder<?> lowStockStore() {
        return Stores.keyValueStoreBuilder(
                Stores.persistentKeyValueStore(LOW_STOCK_STORE),
                Serdes.String(),
                new JsonSerde<>(ProductEvent.class)
        );
    }
}
