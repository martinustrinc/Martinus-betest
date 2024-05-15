package com.javaspring.service;

@Component
public class KafkaConsumer {

    @Autowired
    private UserService userService;

    @KafkaListener(topics = "user-info-topic", groupId = "group-id")
    public void consume(String message) {
        // Convert message to User object and insert into MongoDB
        User user = convertMessageToUser(message);
        userService.createUser(user);
    }

    // Other methods
}
