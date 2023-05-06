package com.sudo248.notification.repository;

import com.google.cloud.firestore.Firestore;
import com.sudo248.notification.repository.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractFirestoreRepository<User>{
    protected UserRepository(Firestore firestore) {
        super(firestore, "users");
    }
}
