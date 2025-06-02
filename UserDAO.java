package dal;

import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<User> getAllUser() {
        List<User> listUser = new ArrayList<>();
        String query = "SELECT * FROM tblUsers";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listUser.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUser;
    }

    public User login(String user, String password) {
        String query = "select * from tblUsers\n"
                + "where account = ?\n"
                + "and password = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User checkAccountExist(String user) {
        String query = "select * from tblUsers\n"
                + "where account = ?\n";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User SignUp(String account, String password, String address, String phone) {
        String query = "insert into tblUsers\n"
                + "values(?,?,2,?,?,1)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, account);
            ps.setString(2, password);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.executeUpdate();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User InsertUsers(String account,
            String password,
            String roleID,
            String address,
            String phone) {
        String query = "insert into tblUsers\n"
                + "values(?,?,?,?,?,1)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, account);
            ps.setString(2, password);
            ps.setString(3, roleID);
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.executeUpdate();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByID(String id) {
        String query = "select * from tblUsers\n"
                + "where userID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(String account,
            String password,
            String roleID,
            String address,
            String phone,
            String isActive,
            String userID) {

        String query = "update tblUsers\n"
                + "set account = ?,\n"
                + "    password = ?,\n"
                + "    roleID = ?,\n"
                + "    address = ?,\n"
                + "    phone = ?,\n"
                + "    isActive = ?\n"
                + "WHERE userID = ?;  ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, account);
            ps.setString(2, password);
            ps.setString(3, roleID);
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.setString(6, isActive);
            ps.setString(7, userID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        List<User> list = dao.getAllUser();
        for (User o : list) {
            //System.out.println(o);
        }
        System.out.println(dao.InsertUsers("kim2", "kim2", "2", "Văn Tiến", "0345064566"));
    }
}
