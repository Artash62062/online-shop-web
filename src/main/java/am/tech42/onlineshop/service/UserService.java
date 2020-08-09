package am.tech42.onlineshop.service;

import am.tech42.onlineshop.db.DatabaseHelper;
import am.tech42.onlineshop.model.User;

import javax.jws.soap.SOAPBinding;
import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UserService {

    private static List<User> users = Collections.singletonList(
            new User(1, "+37495914536", "test")
    );

    public static User login(String phoneNumber, String password) {
        String sql ="select * from  users where phone_number = ? and password = ?";
        try {
            return getUser(phoneNumber, password, sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }




    public static User register (String phoneNumber, String password, String name) {
        String sql = "insert into users(phone_number ,password, name) values (?,?,?)";
        try {
            PreparedStatement ps = DatabaseHelper.getConnection().prepareStatement(sql);
            ps.setString(1,phoneNumber);
            ps.setString(2,password);
            ps.setString(3,name);
            ps.execute();
            String getUserFromDb ="select * from  users where phone_number = ? and password = ?";
            return getUser(phoneNumber, password, getUserFromDb);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
           return  null;
        }

    }

    private static User getUser(String phoneNumber, String password, String getUserFromDb) throws SQLException {
        PreparedStatement ps1 = DatabaseHelper.getConnection().prepareStatement(getUserFromDb);
        ps1.setString(1,phoneNumber);
        ps1.setString(2,password);
        ResultSet rs=ps1.executeQuery();
        rs.next();
        return new User(
                rs.getInt("id"),
                rs.getString("phone_number"),
                rs.getString("password")
        );
    }

}
