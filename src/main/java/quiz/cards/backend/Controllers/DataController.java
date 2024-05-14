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
    @GetMapping("packets")
    public ArrayList<CardsPacket> getPackets(@RequestParam(value = "theme") int themeId) {
        System.out.println("Got packets from theme " + themeId);
        return dbWorker.getPackets(themeId);
    }
    @GetMapping("cards")
    public ArrayList<Card> getCards(@RequestParam(value = "packet") int themeId){
        System.out.println("Got cards from packet " + themeId);
        return dbWorker.getCards(themeId);
    }
}
