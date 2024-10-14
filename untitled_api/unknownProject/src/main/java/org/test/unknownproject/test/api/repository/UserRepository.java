package org.test.unknownproject.test.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.unknownproject.test.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
