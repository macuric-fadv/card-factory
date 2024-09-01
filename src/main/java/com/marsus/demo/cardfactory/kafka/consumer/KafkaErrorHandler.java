package com.marsus.demo.cardfactory.kafka.consumer;

import com.marsus.demo.cardfactory.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.RecordDeserializationException;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;

@Slf4j
public class KafkaErrorHandler implements CommonErrorHandler {

    @Override
    public boolean handleOne(Exception exception, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
        return handle(exception, consumer);
    }

    @Override
    public void handleOtherException(Exception exception, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener) {
        handle(exception, consumer);
    }

    private boolean handle(Exception exception, Consumer<?, ?> consumer) {

        if (exception instanceof RecordDeserializationException ex) {
            log.error("Kafka consumer was unable to deserialize the message, skipping this message", ex);
            consumer.seek(ex.topicPartition(), ex.offset() + 1L);
            consumer.commitSync();
            return true;
        }
        if (exception instanceof IllegalArgumentException ex) {
            log.error("Card request message is invalid", ex);
            return true;
        }
        if (exception instanceof NotFoundException ex) {
            log.error("Card request not found", ex);
            return true;
        }
        log.error("Kafka exception occurred", exception);
        return true;
    }

}
