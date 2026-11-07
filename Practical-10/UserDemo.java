import java.io.*;

class User implements Serializable{

    int id;
    String username;
    transient String password;
    
    User(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public void display(){
        System.out.println("Id : " + id + " | Username : " + username + " | Password : " + password);
    }
}

