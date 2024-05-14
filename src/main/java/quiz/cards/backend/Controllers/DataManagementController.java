package quiz.cards.backend.Controllers;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import quiz.cards.backend.Data.JSONReader;
import quiz.cards.backend.Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data-management")
public class DataManagementController extends AbstractDataController {
    @PostMapping("theme")
    public void createTheme(@RequestBody String json){
        getDbWorker().createTheme(JSONReader.fromJson(json, Theme.class));
        System.out.println("Created theme " + JSONReader.fromJson(json, Theme.class).getName());
    }
    @PutMapping("theme")
    public void updateTheme(@RequestBody String json){
        getDbWorker().updateTheme(JSONReader.fromJson(json, Theme.class));
        System.out.println("Updated theme " + JSONReader.fromJson(json, Theme.class).getName());
    }
    @DeleteMapping("theme")
    public void deleteTheme(@RequestBody String json){
        getDbWorker().deleteTheme(JSONReader.fromJson(json, Theme.class));
        System.out.println("Deleted theme " + JSONReader.fromJson(json, Theme.class).getName());
    }

    @PostMapping("packet")
    public void createPacket(@RequestBody String json){
        getDbWorker().createPacket(JSONReader.fromJson(json, CardsPacket.class));
        System.out.println("Created packet " + JSONReader.fromJson(json, CardsPacket.class).getName() + " in theme " + JSONReader.fromJson(json, CardsPacket.class).getThemeId());
    }
    @PutMapping("packet")
    public void updatePacket(@RequestBody String json){
        getDbWorker().updatePacket(JSONReader.fromJson(json, CardsPacket.class));
        System.out.println("Updated packet " + JSONReader.fromJson(json, CardsPacket.class).getName());
    }
    @DeleteMapping("packet")
    public void deletePacket(@RequestBody String json){
        getDbWorker().deletePacket(JSONReader.fromJson(json, CardsPacket.class));
        System.out.println("Deleted packet " + JSONReader.fromJson(json, CardsPacket.class).getName());
    }

    @PostMapping("card")
    public void createCard(@RequestBody String json){
        getDbWorker().createCard(JSONReader.fromJson(json, Card.class));
        System.out.println("Created card " + JSONReader.fromJson(json, Card.class).getFrontText() + " in packet " + JSONReader.fromJson(json, Card.class).getPacketId());
    }
    @PutMapping("card")
    public void updateCard(@RequestBody String json){
        getDbWorker().updateCard(JSONReader.fromJson(json, Card.class));
        System.out.println("Updated card " + JSONReader.fromJson(json, Card.class).getFrontText());
    }
    @DeleteMapping("card")
    public void deleteCard(@RequestBody String json){
        getDbWorker().deleteCard(JSONReader.fromJson(json, Card.class));
        System.out.println("Deleted card " + JSONReader.fromJson(json, Card.class).getFrontText());
    }
}
