package quiz.cards.backend.Controllers;

import quiz.cards.backend.Model.Card;
import quiz.cards.backend.Model.CardsPacket;
import quiz.cards.backend.Model.Theme;
import quiz.cards.backend.Data.DBWorker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/data")
public class DataController {
    private DBWorker dbWorker = new DBWorker();

    @GetMapping("themes")
    public ArrayList<Theme> getThemes(){
        System.out.println("Got all themes");
        return dbWorker.getThemes();
    }
    @GetMapping("themes/count")
    public int getThemesCount(){
        System.out.println("Got themes count");
        return dbWorker.getThemesCount();
    }
    @GetMapping("packets")
    public ArrayList<CardsPacket> getPackets(@RequestParam(value = "theme") int themeId) {
        System.out.println("Got packets from theme " + themeId);
        return dbWorker.getPackets(themeId);
    }
    @GetMapping("packets/count")
    public int getPacketsCount(int themeId){
        System.out.println("Got packets count from theme " + themeId);
        return dbWorker.getPacketsCount(themeId);
    }
    @GetMapping("cards")
    public ArrayList<Card> getCards(@RequestParam(value = "packet") int themeId){
        System.out.println("Got cards from packet " + themeId);
        return dbWorker.getCards(themeId);
    }
    @GetMapping("cards/count")
    public int getCardsCount(int packetId){
        System.out.println("Got cards count from packet " + packetId);
        return dbWorker.getCardsCount(packetId);
    }
}
