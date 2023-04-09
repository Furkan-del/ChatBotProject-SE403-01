package com.example.chatbot.dataAccesLayer;

import com.example.chatbot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


     User findByUserName(String userName);
    User findByEmail(String email);

}
