package com.ozeemm.quizcards;

import Model.Card;
import Model.CardsPacket;
import Model.Theme;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data-management")
public class DataManagementController {
    DBWorker dbWorker = new DBWorker();

    @PostMapping("theme")
    public void createTheme(@RequestBody String themeJson){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Theme theme = objectMapper.readValue(themeJson, Theme.class);

            dbWorker.createTheme(theme);
        } catch(Exception e) { e.printStackTrace(); }
    }
    @PutMapping("theme")
    public void updateTheme(@RequestBody String themeJson){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Theme theme = objectMapper.readValue(themeJson, Theme.class);

            dbWorker.updateTheme(theme);
        } catch(Exception e) { e.printStackTrace(); }
    }
    @DeleteMapping("theme")
    public void deleteTheme(@RequestBody String themeJson){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Theme theme = objectMapper.readValue(themeJson, Theme.class);

            dbWorker.deleteTheme(theme);
        } catch(Exception e) { e.printStackTrace(); }
    }

    @PostMapping("packet")
    public void createPacket(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String[] dataList = objectMapper.readValue(json, String[].class);

            Theme theme = objectMapper.readValue(dataList[0], Theme.class);
            CardsPacket packet = objectMapper.readValue(dataList[1], CardsPacket.class);

            dbWorker.createPacket(theme, packet);
        } catch(Exception e){ e.printStackTrace(); }
    }
    @PutMapping("packet")
    public void updatePacket(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CardsPacket packet = objectMapper.readValue(json, CardsPacket.class);

            dbWorker.updatePacket(packet);
        } catch(Exception e){ e.printStackTrace(); }
    }
    @DeleteMapping("packet")
    public void deletePacket(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CardsPacket packet = objectMapper.readValue(json, CardsPacket.class);

            dbWorker.deletePacket(packet);
        } catch(Exception e){ e.printStackTrace(); }
    }

    @PostMapping("card")
    public void createCard(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String[] dataList = objectMapper.readValue(json, String[].class);

            CardsPacket packet = objectMapper.readValue(dataList[0], CardsPacket.class);
            Card card = objectMapper.readValue(dataList[1], Card.class);

            dbWorker.createCard(packet, card);
        } catch(Exception e){ e.printStackTrace(); }
    }
    @PutMapping("card")
    public void updateCard(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Card card = objectMapper.readValue(json, Card.class);

            dbWorker.updateCard(card);
        } catch(Exception e){ e.printStackTrace(); }
    }
    @DeleteMapping("card")
    public void deleteCard(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Card card = objectMapper.readValue(json, Card.class);

            dbWorker.deleteCard(card);
        } catch(Exception e){ e.printStackTrace(); }
    }
}
