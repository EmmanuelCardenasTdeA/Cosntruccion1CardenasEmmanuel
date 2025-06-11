package app.adapters.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.adapters.users.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    public boolean existsByUserName(String userName);
    public UserEntity findByPersonDocument(long personDocument);
    public UserEntity findByUserName(String userName);
    public UserEntity findByUserId(long userId);

} 

