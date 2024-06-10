package quiz.cards.backend.Controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import quiz.cards.backend.Data.DBWorker;
@SecurityRequirement(name="bearerAuth")
public abstract class AbstractDataController {
    private final DBWorker dbWorker = new DBWorker();
    public DBWorker getDbWorker(){ return dbWorker; }
}
