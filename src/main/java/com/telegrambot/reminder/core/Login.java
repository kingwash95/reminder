package com.telegrambot.reminder.core;

import java.sql.*;

public class Login {
    private Connection con; // well, here we saved 7 bytes on extra 'nection' symbols
    private Statement st;   // and here another 7, so 14 in total
    private ResultSet rs;
    public Login(){ // camelcase please, this is not C#
        try{    // indentation?
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/reminder", "postgres", "admin" );   // move all this stuff to application.properties
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    public String add(int chatId, Date date, String text){
        String sql = "INSERT INTO notes( chatId, date, text ) VALUES (?,?,?)";  // missed ')'
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, chatId);
            st.setDate(2, date);
            st.setString(3, text);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(("Error " + e));
            return "Информацию не удалось добавить!";
        }
        return "Информация добавлена";
    }

    public static void main(String[] args) {
        Login login = new Login();
        login.add(23, null, "fsdfsdf");
    }
}