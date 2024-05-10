package quiz.cards.backend.Controllers;

import quiz.cards.backend.Model.Card;
import quiz.cards.backend.Model.CardsPacket;
import quiz.cards.backend.Model.Theme;
import com.fasterxml.jackson.databind.ObjectMapper;
import quiz.cards.backend.Data.DBWorker;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data-management")
public class DataManagementController {
    DBWorker dbWorker = new DBWorker();

    @PostMapping("theme")
    public void createTheme(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Theme theme = objectMapper.readValue(json, Theme.class);

            dbWorker.createTheme(theme);
            System.out.println("Created theme " + theme.getName());
        } catch(Exception e) { e.printStackTrace(); }
    }
    @PutMapping("theme")
    public void updateTheme(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Theme theme = objectMapper.readValue(json, Theme.class);

            dbWorker.updateTheme(theme);
            System.out.println("Updated theme " + theme.getName());
        } catch(Exception e) { e.printStackTrace(); }
    }
    @DeleteMapping("theme")
    public void deleteTheme(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Theme theme = objectMapper.readValue(json, Theme.class);

            dbWorker.deleteTheme(theme);
            System.out.println("Deleted theme " + theme.getName());
        } catch(Exception e) { e.printStackTrace(); }
    }

    @PostMapping("packet")
    public void createPacket(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CardsPacket packet = objectMapper.readValue(json, CardsPacket.class);

            dbWorker.createPacket(packet);
            System.out.println("Created packet " + packet.getName() + " in theme " + packet.getThemeId());
        } catch(Exception e){ e.printStackTrace(); }
    }
    @PutMapping("packet")
    public void updatePacket(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CardsPacket packet = objectMapper.readValue(json, CardsPacket.class);

            dbWorker.updatePacket(packet);
            System.out.println("Updated packet " + packet.getName());
        } catch(Exception e){ e.printStackTrace(); }
    }
    @DeleteMapping("packet")
    public void deletePacket(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CardsPacket packet = objectMapper.readValue(json, CardsPacket.class);

            dbWorker.deletePacket(packet);
            System.out.println("Deleted packet " + packet.getName());
        } catch(Exception e){ e.printStackTrace(); }
    }

    @PostMapping("card")
    public void createCard(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Card card = objectMapper.readValue(json, Card.class);

            dbWorker.createCard(card);
            System.out.println("Created card " + card.getFrontText() + " in packet " + card.getPacketId());
        } catch(Exception e){ e.printStackTrace(); }
    }
    @PutMapping("card")
    public void updateCard(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Card card = objectMapper.readValue(json, Card.class);

            dbWorker.updateCard(card);
            System.out.println("Updated card " + card.getFrontText());
        } catch(Exception e){ e.printStackTrace(); }
    }
    @DeleteMapping("card")
    public void deleteCard(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Card card = objectMapper.readValue(json, Card.class);

            dbWorker.deleteCard(card);
            System.out.println("Deleted card " + card.getFrontText());
        } catch(Exception e){ e.printStackTrace(); }
    }
}
