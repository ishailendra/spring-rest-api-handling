package dev.techsphere.restapitester.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RestApiTrigger {

    @Autowired
    private RestApiConsumer consumer;

    @Scheduled(initialDelay = 5000, fixedDelay = 18000000)
    public void scheduleRestJob() {
        consumer.getStudent();
        consumer.getStudentAsJson();
        consumer.addNewStudent();
        consumer.addNewStudentExchange();
        consumer.updateStudentWithExchange();
    }
}
