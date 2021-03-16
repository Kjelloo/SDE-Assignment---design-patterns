/**
 * @author kjell
 */

package com.redheads.patterns.dal;


import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.redheads.patterns.be.Message;
import com.redheads.patterns.utils.db.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository implements IMessageRepository{
    private DBConnector con;

    public MessageRepository() {
        con = new DBConnector();
    }

    @Override
    public Message sendMessage(String msg) {
        try (Connection connection = con.getConnection()) {
            String sql = "INSERT INTO Messages (Text) VALUES (?);";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, msg);
            statement.execute();

            try(ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    return new Message(keys.getInt(1), msg);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Message getMessage(int id) {
        try(Connection connection = con.getConnection()){
            String sql = "SELECT * FROM Messages WHERE ID=?;";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, id);

            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int newID = resultSet.getInt("ID");
                    String text = resultSet.getString("Text");

                    return new Message(newID, text);
                }
            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Message> getAll() {
        List<Message> msgList = new ArrayList<>();
        try(Connection connection = con.getConnection()){
            String sql = "SELECT * FROM Messages;";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int newID = resultSet.getInt("ID");
                    String text = resultSet.getString("Text");

                    msgList.add(new Message(newID, text));
                }
            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return msgList;
    }
}
