package csci318.group10.analyticsservice.applicationservice;

import csci318.group10.analyticsservice.shareddomain.events.CartEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;


import java.util.function.Function;


@Configuration
public class StreamProcessor {


    @Bean
    public Function<KStream<String, CartEvent>, KStream<String, Long>> process() {
        return inputStream -> inputStream
                .filter((key, value) -> value != null && value.getItems() != null && !value.getItems().isEmpty())
                .selectKey((key, value) -> String.valueOf(value.getCartId()))
                .groupByKey(Grouped.with(Serdes.String(), new JsonSerde<>(CartEvent.class)))
                .aggregate(
                        () -> 0L,
                        (key, value, aggregate) -> (long) value.getItems().size(),
                        Materialized.with(Serdes.String(), Serdes.Long())
                )
                .toStream();
    }
}
