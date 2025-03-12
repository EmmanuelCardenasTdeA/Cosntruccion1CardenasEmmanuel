package app.adapters.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.adapters.users.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

    public boolean existsByUserName(String userName);
    public UserEntity findByPersonDocument(long personDocument);
} 

