package com.telegrambot.reminder.classes;

import java.sql.*;

public class Login {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    public Login(){
        try{
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/reminder", "postgres", "admin" );
        st = con.createStatement();
        } catch (Exception e) {
           System.out.println("Error: " + e);
        }
    }
    public String add(String chat_id, Date date, String text){
        String sql = "INSERT INTO notes(chat_id,date,text) VALUES (?,?,?";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, chat_id);
            st.setDate(2, date);
            st.setString(3, text);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(("Error " + e));
            return "Информацию не удалось добавить!";
        }
        return "Информация добавлена";
    }
}
