package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public User findByName(String name);

    public User findByID(String id);

    public List<User> findAll();

//    public User updateByID(String id, User user);

    public boolean deleteByID(String id);

}
//    private MongoCollection<Document> userCollection;
//
//    public UserRepository() {
//        MongoDatabase database = MongoDBConnection.getDatabase();
//        this.userCollection = database.getCollection("Users");
//    }
//
//    public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//        for (Document doc : userCollection.find()) {
//            User user = new User(
//                    doc.getInteger("id"),
//                    doc.getString("name"),
//                    doc.getString("email")
//            );
//            users.add(user);
//        }
//        return users;
//    }
//
//    public User getUserById(int id) {
//        Document doc = userCollection.find(Filters.eq("id", id)).first();
//        if (doc != null) {
//            return new User(
//                    doc.getInteger("id"),
//                    doc.getString("name"),
//                    doc.getString("email")
//            );
//        }
//        return null;
//    }
//
//    public boolean addUser(User user) {
//        Document
//        userCollection.insertOne(user);
//    }
//}
//
//
//    public boolean add(User user) {
//        users.add(user);
//        return true;
//    }
//    public boolean deleteUser(Long id) {
//        for (int i = 0; i < users.size(); i++) {
//            User user = users.get(i);
//            if (user.getID() == id) {
//                users.remove(i);
//                return true;
//            }
//        }
//        return false;
//    }
//    public User updateUser(Long id, User updatedUser) {
//        for (int i = 0; i < users.size(); i++) {
//            User user = users.get(i);
//            if (user.getID() == id) {
//                users.set(i, updatedUser);
//                return updatedUser;
//            }
//        }
//        return null;
//    }
//
//}
