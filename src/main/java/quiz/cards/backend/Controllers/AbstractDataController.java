package quiz.cards.backend.Controllers;

import quiz.cards.backend.Data.DBWorker;

public abstract class AbstractDataController {
    private final DBWorker dbWorker = new DBWorker();
    public DBWorker getDbWorker(){ return dbWorker; }
}
