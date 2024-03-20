package com.venkatesh.kafkaproducer.service;

import com.venkatesh.kafkaproducer.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("kafka-demo2", message);
        future.whenComplete((result,ex)->
        {
            if(ex==null){
                System.out.println("Sent message=["+message+"] with offset=["+result.getRecordMetadata().offset()+"]");
            }else {
                System.out.println("Unable to send message=["+message+"[ due to : "+ex.getMessage());
            }
        });
    }
    public void sendEmployeeToTopic(Employee employee){
        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("kafka-demo2", employee);
            future.whenComplete((result, ex) ->
                    {
                        if (ex == null) {
                            System.out.println("Sent message=[" + employee + "] with offset=[" + result.getRecordMetadata().offset() + "]");
                        } else {
                            System.out.println("Unable to send message=[" + employee + "[ due to : " + ex.getMessage());
                        }
                    }
            );
        }catch (Exception exception){
            System.out.println("Exception occurred "+exception.getMessage());
        }
    }
}
