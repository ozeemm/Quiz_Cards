package com.ozeemm.quizcards;

import Model.Card;
import Model.CardsPacket;
import Model.Theme;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AdminController {
    private DBWorker dbWorker = new DBWorker();
    @GetMapping("Themes")
    public ArrayList<Theme> getThemes(){
        return dbWorker.getThemes();
    }
    @GetMapping("Packets")
    public ArrayList<CardsPacket> getPackets(@RequestBody String themeJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Theme theme = objectMapper.readValue(themeJson, Theme.class);
            return dbWorker.getPackets(theme);
        } catch(JsonProcessingException e){ e.printStackTrace(); }
        return null;
    }
    @GetMapping("Cards")
    public ArrayList<Card> getCards(@RequestBody String packetJson){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CardsPacket packet = objectMapper.readValue(packetJson, CardsPacket.class);
            return dbWorker.getCards(packet);
        } catch(JsonProcessingException e){ e.printStackTrace(); }
        return null;
    }

    @PostMapping("CreateTheme")
    public void createTheme(@RequestBody String themeJson){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Theme theme = objectMapper.readValue(themeJson, Theme.class);

            dbWorker.createTheme(theme);
        } catch(Exception e) { e.printStackTrace(); }
    }
    @PostMapping("UpdateTheme")
    public void updateTheme(@RequestBody String themeJson){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Theme theme = objectMapper.readValue(themeJson, Theme.class);

            dbWorker.updateTheme(theme);
        } catch(Exception e) { e.printStackTrace(); }
    }
    @PostMapping("DeleteTheme")
    public void deleteTheme(@RequestBody String themeJson){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Theme theme = objectMapper.readValue(themeJson, Theme.class);

            dbWorker.deleteTheme(theme);
        } catch(Exception e) { e.printStackTrace(); }
    }

    @PostMapping("CreatePacket")
    public void createPacket(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String[] dataList = objectMapper.readValue(json, String[].class);

            Theme theme = objectMapper.readValue(dataList[0], Theme.class);
            CardsPacket packet = objectMapper.readValue(dataList[1], CardsPacket.class);

            dbWorker.createPacket(theme, packet);
        } catch(Exception e){ e.printStackTrace(); }
    }
    @PostMapping("UpdatePacket")
    public void updatePacket(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CardsPacket packet = objectMapper.readValue(json, CardsPacket.class);

            dbWorker.updatePacket(packet);
        } catch(Exception e){ e.printStackTrace(); }
    }
    @PostMapping("DeletePacket")
    public void deletePacket(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CardsPacket packet = objectMapper.readValue(json, CardsPacket.class);

            dbWorker.deletePacket(packet);
        } catch(Exception e){ e.printStackTrace(); }
    }

    @PostMapping("CreateCard")
    public void createCard(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String[] dataList = objectMapper.readValue(json, String[].class);

            CardsPacket packet = objectMapper.readValue(dataList[0], CardsPacket.class);
            Card card = objectMapper.readValue(dataList[1], Card.class);

            dbWorker.createCard(packet, card);
        } catch(Exception e){ e.printStackTrace(); }
    }
    @PostMapping("UpdateCard")
    public void updateCard(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Card card = objectMapper.readValue(json, Card.class);

            dbWorker.updateCard(card);
        } catch(Exception e){ e.printStackTrace(); }
    }
    @PostMapping("DeleteCard")
    public void deleteCard(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Card card = objectMapper.readValue(json, Card.class);

            dbWorker.deleteCard(card);
        } catch(Exception e){ e.printStackTrace(); }
    }
}
