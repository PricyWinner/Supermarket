package Services;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import Models.Transaction;
import Models.User;

public class UserServices extends AppCompatActivity {

    public static List<User> listUser = new ArrayList<>();

    public static User currentUser = null;

    public static User getUser(int userId){
        User user = null;

        for(int i = 0; i < listUser.size(); i++){
            if(listUser.get(i).getUserId() == userId){
                user = listUser.get(i);
                break;
            }
        }

        return user;
    }

    public static void addUser(User user){
        listUser.add(user);
    }
}
