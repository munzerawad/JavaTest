


package models;

import java.util.ArrayList;
import java.util.List;




public class Repository {
    
    public List<User> users=new ArrayList<>();
    public Repository(){
        users.add(new User(1,"First","somwhere 1"));
        users.add(new User(2,"Second","somwhere 13"));
        users.add(new User(3,"Third","somwhere 15"));
        users.add(new User(5,"Fourth","somwhere 122"));
    }
    
    public User findUser(int id)
    {
        User user=null;
        try {
            user=users.stream()
                    .filter(u->u.getId()==id)
                    .findFirst()
                    .get();
        } catch (Exception e) {
        }
        
        return  user;
    }
    
}
