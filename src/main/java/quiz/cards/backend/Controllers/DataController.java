package quiz.cards.backend.Controllers;

import quiz.cards.backend.Model.*;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/data")
public class DataController extends AbstractDataController {
    @GetMapping("themes")
    public ArrayList<Theme> getThemes(){
        System.out.println("Got all themes");
        return getDbWorker().getThemes();
    }
    @GetMapping("packets")
    public ArrayList<CardsPacket> getPackets(@RequestParam(value = "theme") int themeId) {
        System.out.println("Got packets from theme " + themeId);
        return getDbWorker().getPackets(themeId);
    }
    @GetMapping("cards")
    public ArrayList<Card> getCards(@RequestParam(value = "packet") int packetId){
        System.out.println("Got cards from packet " + packetId);
        return getDbWorker().getCards(packetId);
    }
}
