package am.tech42.onlineshop.service;

import am.tech42.onlineshop.db.DatabaseHelper;
import am.tech42.onlineshop.model.PostHeader;
import com.sun.org.apache.xpath.internal.objects.XObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PostService {

    private static final int TOTAL_COUNT_PER_PAGE = 2;

    public static int getTotalPages() throws SQLException {
        String sql = "select ceil(count(*)::float / ?) as total_pages from posts where published = true";
        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, TOTAL_COUNT_PER_PAGE);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getInt("total_pages");
    }

    public static List<PostHeader> getPostHeaders(int pageNumber) throws SQLException {
        String sql = "select p.id, p.header, p.price, (select path from images where post_id = p.id limit 1) from posts p limit ? offset ?";
        PreparedStatement preparedStatement = DatabaseHelper.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, TOTAL_COUNT_PER_PAGE);
        preparedStatement.setInt(2, (pageNumber - 1) * TOTAL_COUNT_PER_PAGE);

        ResultSet resultSet = preparedStatement.executeQuery();
        List postHeaders = new ArrayList<PostHeader>();

        while (resultSet.next()) {
            postHeaders.add(new PostHeader(
                    resultSet.getInt("id"),
                    resultSet.getString("path"),
                    resultSet.getString("header"),
                    resultSet.getFloat("price")
            ));
        }

        return postHeaders;
    }

}
