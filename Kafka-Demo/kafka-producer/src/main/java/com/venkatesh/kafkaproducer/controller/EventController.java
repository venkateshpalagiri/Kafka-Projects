package com.venkatesh.kafkaproducer.controller;

import com.venkatesh.kafkaproducer.dto.Employee;
import com.venkatesh.kafkaproducer.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class EventController {
    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;
    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message){
        try {
            kafkaMessagePublisher.sendMessageToTopic(message);
            return ResponseEntity.ok("Message published successfully...");
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }

    }
    //Sending bulk message to check which partition handling
    @GetMapping("/bulkpublish/{bulkMessage}")
    public ResponseEntity<?> publishBulkMessage(@PathVariable String bulkMessage){
        try{
            for(int i=0;i<=10000;i++){
                kafkaMessagePublisher.sendMessageToTopic(bulkMessage+":"+i);
            }
            return ResponseEntity.ok("Message published successfully...");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }
    @PostMapping("/publishEmployee")
    public ResponseEntity<?> publishEmployeeObject(@RequestBody Employee employee) {
        try {
            kafkaMessagePublisher.sendEmployeeToTopic(employee);
            return ResponseEntity.ok("Employee object published successfully...");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }
}
