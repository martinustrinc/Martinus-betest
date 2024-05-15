package com.javaspring.service;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RedisTemplate<String, User> redisTemplate;

    @Test
    public void testGetUserByAccountNumber() {
        // Mock Redis cache
        when(redisTemplate.opsForValue().get("user:123")).thenReturn(null);

        // Mock UserRepository
        User mockUser = new User("John Doe", "123456", "john@example.com", "A123");
        when(userRepository.findByAccountNumber("123")).thenReturn(mockUser);

        // Test getUserByAccountNumber
        User user = userService.getUserByAccountNumber("123");
        assertEquals("John Doe", user.getFullName());
        assertEquals("123456", user.getAccountNumber());
        assertEquals("john@example.com", user.getEmailAddress());
        assertEquals("A123", user.getRegistrationNumber());
    }

    // Other test cases
}
