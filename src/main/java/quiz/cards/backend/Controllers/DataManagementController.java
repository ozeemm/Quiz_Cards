package quiz.cards.backend.Controllers;

import quiz.cards.backend.Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data-management")
public class DataManagementController extends AbstractDataController {
    @PostMapping("theme")
    public void createTheme(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Theme theme = objectMapper.readValue(json, Theme.class);

            getDbWorker().createTheme(theme);
            System.out.println("Created theme " + theme.getName());
        } catch(Exception e) { e.printStackTrace(); }
    }
    @PutMapping("theme")
    public void updateTheme(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Theme theme = objectMapper.readValue(json, Theme.class);

            getDbWorker().updateTheme(theme);
            System.out.println("Updated theme " + theme.getName());
        } catch(Exception e) { e.printStackTrace(); }
    }
    @DeleteMapping("theme")
    public void deleteTheme(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Theme theme = objectMapper.readValue(json, Theme.class);

            getDbWorker().deleteTheme(theme);
            System.out.println("Deleted theme " + theme.getName());
        } catch(Exception e) { e.printStackTrace(); }
    }

    @PostMapping("packet")
    public void createPacket(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CardsPacket packet = objectMapper.readValue(json, CardsPacket.class);

            getDbWorker().createPacket(packet);
            System.out.println("Created packet " + packet.getName() + " in theme " + packet.getThemeId());
        } catch(Exception e){ e.printStackTrace(); }
    }
    @PutMapping("packet")
    public void updatePacket(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CardsPacket packet = objectMapper.readValue(json, CardsPacket.class);

            getDbWorker().updatePacket(packet);
            System.out.println("Updated packet " + packet.getName());
        } catch(Exception e){ e.printStackTrace(); }
    }
    @DeleteMapping("packet")
    public void deletePacket(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CardsPacket packet = objectMapper.readValue(json, CardsPacket.class);

            getDbWorker().deletePacket(packet);
            System.out.println("Deleted packet " + packet.getName());
        } catch(Exception e){ e.printStackTrace(); }
    }

    @PostMapping("card")
    public void createCard(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Card card = objectMapper.readValue(json, Card.class);

            getDbWorker().createCard(card);
            System.out.println("Created card " + card.getFrontText() + " in packet " + card.getPacketId());
        } catch(Exception e){ e.printStackTrace(); }
    }
    @PutMapping("card")
    public void updateCard(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Card card = objectMapper.readValue(json, Card.class);

            getDbWorker().updateCard(card);
            System.out.println("Updated card " + card.getFrontText());
        } catch(Exception e){ e.printStackTrace(); }
    }
    @DeleteMapping("card")
    public void deleteCard(@RequestBody String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Card card = objectMapper.readValue(json, Card.class);

            getDbWorker().deleteCard(card);
            System.out.println("Deleted card " + card.getFrontText());
        } catch(Exception e){ e.printStackTrace(); }
    }
}
