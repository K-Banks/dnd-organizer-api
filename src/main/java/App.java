import com.google.gson.Gson;
import dao.Sql2oCharacterDao;
import dao.Sql2oClassDao;
import dao.Sql2oSpellDao;
import models.Class;
import models.Character;
import models.Spell;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        Sql2oSpellDao spellDao;
        Sql2oClassDao classDao;
        Sql2oCharacterDao characterDao;
        Connection con;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/DnDSpells.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        spellDao = new Sql2oSpellDao(sql2o);
        classDao = new Sql2oClassDao(sql2o);
        characterDao = new Sql2oCharacterDao(sql2o);
        con = sql2o.open();

        //GET all spells
        get("/spells", "application/json", (request, response) -> {
            List<Spell> allSpells = spellDao.getAll();
            if (allSpells.size()<=0) {
                response.status(404);
                return gson.toJson(String.format("Sorry, there are no spells available."));
            } else {
                response.status(200);
                return gson.toJson(allSpells);
            }
        });

//        //GET all classes
//        get("/classes", "application/json", (request, response) -> {});
//
//        //GET all characters
//        get("/characters", "application/json", (request, response) -> {});
//
//        //GET spell details by id
//        get("/spells/:spellId", "application/json", (request, response) -> {});
//
//        //GET character details by id
//        get("/characters/:characterId", "application/json", (request, response) -> {});
//
//        //GET class details by id
//        get("/classes/:classId", "application/json", (request, response) -> {});
//
//        //GET all spells for a class
//        get("/classes/:classId/spells", "application/json", (request, response) -> {});
//
//        //POST new character
//        post("/characters/add", "application/json", (request, response) -> {});
//
//        //POST new class
//        post("/classes/add", "application/json", (request, response) -> {});

        //POST new spell
        post("/spells/add", "application/json", (request, response) -> {
            Spell spell = gson.fromJson(request.body(), Spell.class);
            spellDao.add(spell);
            Spell spellAdded = spellDao.findById(spell.getId());
            response.status(201);
            response.type("application/json");
            return gson.toJson(spellAdded);
        });

//        //POST assign spell to a class
//        post("/class/:classId/spells/:spellId/add", "application/json", (request, response) -> {});
//
//        //POST assign class to a character
//        post("/characters/:characterId/classes/:classId/add", "application/json", (request, response) -> {});
//
//        //UPDATE character by id
//        put("/characters/:characterId/update", "application/json", (request, response) -> {});
//
//        //UPDATE class by id
//        put("/classes/:classId/update", "application/json", (request, response) -> {});
//
//        //UPDATE spell by id
//        put("/spells/:spellId/update", "application/json", (request, response) -> {});
//
//        //DELETE character by id
//        delete("/characters/:characterId", "application/json", (request, response) -> {});
//
//        //DELETE class by id
//        delete("/classes/:classId", "application/json", (request, response) -> {});
//
//        //DELETE spell by id
//        delete("/spells/:spellId", "application/json", (request, response) -> {});
    }
}
