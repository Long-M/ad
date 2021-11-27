package com.ml.ad.sender.kafka;

import com.alibaba.fastjson.JSON;
import com.ml.ad.mysql.dto.MySQLRowData;
import com.ml.ad.sender.ISender;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Mr.ml
 * @date 2021/11/27
 */
@Slf4j
@Component("kafkaSender")
public class KafkaSender implements ISender {

    @Value("${adconf.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(MySQLRowData rowData) {
        kafkaTemplate.send(topic, JSON.toJSONString(rowData));
    }

    /**
     * 消息舰艇
     *
     * @param record
     */
    @KafkaListener(topics = {"ad-search-mysql-data"}, groupId = "ad-search")
    public void processMysqlRowData(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            MySQLRowData rowData = JSON.parseObject(message.toString(), MySQLRowData.class);

            System.out.println("kafka processMysqlRowData: " + JSON.toJSONString(rowData));
        }
    }

}
