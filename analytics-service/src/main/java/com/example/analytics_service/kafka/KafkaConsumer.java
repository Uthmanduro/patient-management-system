package com.example.analytics_service.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics="patients", groupId = "analytics-service")
    public void consumeEvent(byte[] event) {
        try {
            PatientEvent patientEvent =  PatientEvent.parseFrom(event);
            // perform other business logic related to analytics
            log.info("Received patient event from the producer {}, {}, {}", patientEvent.getName(), patientEvent.getEmail(), patientEvent.getPatientId());
        } catch (InvalidProtocolBufferException e) {
            log.error("Error parsing event {}", e.getMessage());

        }
    }

}
