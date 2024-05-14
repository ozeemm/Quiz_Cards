package quiz.cards.backend.Controllers;

import quiz.cards.backend.Data.DBWorker;

public class AbstractDataController {
    private DBWorker dbWorker = new DBWorker();
    public DBWorker getDbWorker(){ return dbWorker; }
}
