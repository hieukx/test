package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Category;

public class CategoryDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Category> getAllCategory() {
        List<Category> listCategory = new ArrayList<>();
        String query = "SELECT * FROM tblCategories";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listCategory.add(new Category(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)));
            }

        } catch (Exception e) {
        }
        return listCategory;
    }

    public Category getCategoryByID(String id) {
        String query = "select * from tblCategories\n"
                + "where categoryID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Category(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Category InsertCategory(
            String categoryName,
            String description) {
        String query = "insert into tblCategories\n"
                + "values(?,?,1)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, categoryName);
            ps.setString(2, description);
            ps.executeUpdate();
            while (rs.next()) {
                return new Category(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCategory(String name,
            String description,
            String active,
            String categoryID) {

        String query = "update tblCategories\n"
                + "set categoryName = ?,\n"
                + "description = ?,\n"
                + "active = ?\n"
                + "WHERE categoryID =?;  ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, active);
            ps.setString(4, categoryID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CategoryDAO cdao = new CategoryDAO();
        List<Category> clist = cdao.getAllCategory();
        for (Category o : clist) {
            //System.out.println(o);
        }
        System.out.println(cdao.InsertCategory("d", "d"));
    }
}
