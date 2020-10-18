package com.lucas.security.repositories;

import static com.lucas.security.uuid.UuidGenerator.generateType3UUID;
import static com.lucas.security.uuid.UuidGenerator.zeroUuid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.lucas.security.models.User;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void should_Generate_Uuid_Based_On_Email_Properly() {

        final String email = "lucas@gmail.com";
        final User user = User.builder()
                .name("Lucas Crocomo")
                .email(email)
                .build();

        repository.save(user);

        final User got = repository.findById(generateType3UUID(zeroUuid, email))
                .orElse(null);

        assertNotNull(got);
        assertEquals("Lucas Crocomo", got.getName());
    }

}