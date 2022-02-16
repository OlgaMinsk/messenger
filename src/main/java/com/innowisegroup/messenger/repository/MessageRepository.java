package com.innowisegroup.messenger.repository;

import com.innowisegroup.messenger.model.Message;
import com.innowisegroup.messenger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> getMessageByReceiver(User receiver);

    List<Message> getMessageBySender(User sender);
}
