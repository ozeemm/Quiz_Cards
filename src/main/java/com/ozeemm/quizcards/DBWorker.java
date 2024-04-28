package com.ozeemm.quizcards;

import Model.Card;
import Model.CardsPacket;
import Model.Theme;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DBWorker {
    private final String url = "jdbc:postgresql://localhost:5432/quiz_cards";
    private final String user = "postgres";
    private final String password = "postgres";
    private Connection connection;
    public DBWorker(){
        connect();
    }
    public void connect(){
        Properties auth = new Properties();
        auth.setProperty("user", user);
        auth.setProperty("password", password);

        try{
            connection = DriverManager.getConnection(url, auth);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void closeConnection(){
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Theme> getThemes(){
        try{
            ArrayList<Theme> themes = new ArrayList<Theme>();

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM themes";
            ResultSet table = statement.executeQuery(query);

            while(table.next()){
                Theme theme = new Theme();
                theme.setId(table.getInt("id"));
                theme.setName(table.getString("name"));
                theme.setDescription(table.getString("description"));
                themes.add(theme);
            }
            table.close();
            statement.close();

            return themes;
        } catch (SQLException e){ e.printStackTrace(); }
        return null;
    }
    public ArrayList<CardsPacket> getPackets(Theme theme){
        try{
            ArrayList<CardsPacket> packets = new ArrayList<CardsPacket>();

            String query = "select * from card_packets where id in (select packet_id from packets_themes_rel where theme_id = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, theme.getId());

            ResultSet table = statement.executeQuery();

            while(table.next()){
                CardsPacket packet = new CardsPacket();
                packet.setId(table.getInt("id"));
                packet.setName(table.getString("name"));
                packet.setDescription(table.getString("description"));

                packets.add(packet);
            }
            table.close();
            statement.close();

            return packets;
        } catch (SQLException e){ e.printStackTrace(); }
        return null;
    }
    public ArrayList<Card> getCards(CardsPacket packet){
        try{
            ArrayList<Card> cards = new ArrayList<Card>();

            String query = "select * from cards where id in (select card_id from cards_packets_rel where packet_id = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, packet.getId());

            ResultSet table = statement.executeQuery();

            while(table.next()){
                Card card = new Card();
                card.setId(table.getInt("id"));
                card.setFrontText(table.getString("front_text"));
                card.setBackText(table.getString("back_text"));
                cards.add(card);
            }
            table.close();
            statement.close();

            return cards;
        } catch (SQLException e){ e.printStackTrace(); }
        return null;
    }

    public void createTheme(Theme theme){
        try{
            String query = "INSERT INTO themes(name, description) values(?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, theme.getName());
            statement.setString(2, theme.getDescription());
            statement.execute();
            statement.close();
        } catch(SQLException e){ e.printStackTrace(); }
    }
    public void updateTheme(Theme theme){
        try{
            String query = "UPDATE themes SET name=?, description=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, theme.getName());
            statement.setString(2, theme.getDescription());
            statement.setInt(3, theme.getId());
            statement.execute();
            statement.close();
        } catch(SQLException e){ e.printStackTrace(); }
    }
    public void deleteTheme(Theme theme){
        try{
            String query = "DELETE FROM themes WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, theme.getId());
            statement.execute();
            statement.close();
        } catch(SQLException e){ e.printStackTrace(); }
    }

    public void createPacket(Theme theme, CardsPacket packet){
        try{
            String query = "INSERT INTO card_packets(name, description) values(?, ?) RETURNING id";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, packet.getName());
            statement.setString(2, packet.getDescription());
            ResultSet table = statement.executeQuery();
            table.next();
            int packetId = table.getInt("id");
            table.close();

            query = "INSERT INTO packets_themes_rel(theme_id, packet_id) VALUES(?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, theme.getId());
            statement.setInt(2, packetId);
            statement.execute();

            statement.close();
        } catch(SQLException e){ e.printStackTrace(); }
    }
    public void updatePacket(CardsPacket packet){
        try{
            String query = "UPDATE card_packets SET name=?, description=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, packet.getName());
            statement.setString(2, packet.getDescription());
            statement.setInt(3, packet.getId());
            statement.execute();
            statement.close();
        } catch(SQLException e){ e.printStackTrace(); }
    }
    public void deletePacket(CardsPacket packet){
        try{
            String query = "DELETE FROM card_packets WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, packet.getId());
            statement.execute();
            statement.close();
        } catch(SQLException e){ e.printStackTrace(); }
    }

    public void createCard(CardsPacket packet, Card card){
        try{
            String query = "INSERT INTO cards(front_text, back_text) values(?, ?) RETURNING id";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, card.getFrontText());
            statement.setString(2, card.getFrontText());
            ResultSet table = statement.executeQuery();
            table.next();
            int cardId = table.getInt("id");
            table.close();

            query = "INSERT INTO cards_packets_rel(packet_id, card_id) VALUES(?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, packet.getId());
            statement.setInt(2, cardId);
            statement.execute();

            statement.close();
        } catch(SQLException e){ e.printStackTrace(); }
    }
    public void updateCard(Card card){
        try{
            String query = "UPDATE cards SET front_text=?, back_text=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, card.getFrontText());
            statement.setString(2, card.getBackText());
            statement.setInt(3, card.getId());
            statement.execute();
            statement.close();
        } catch(SQLException e){ e.printStackTrace(); }
    }
    public void deleteCard(Card card){
        try{
            String query = "DELETE FROM cards WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, card.getId());
            statement.execute();
            statement.close();
        } catch(SQLException e){ e.printStackTrace(); }
    }
}
