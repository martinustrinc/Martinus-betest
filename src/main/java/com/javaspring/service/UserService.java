package com.javaspring.service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    public User getUserByAccountNumber(String accountNumber) {
        String cacheKey = "user:" + accountNumber;
        User user = redisTemplate.opsForValue().get(cacheKey);
        if (user == null) {
            user = userRepository.findByAccountNumber(accountNumber);
            redisTemplate.opsForValue().set(cacheKey, user);
        }
        return user;
    }

    // Other methods
}
