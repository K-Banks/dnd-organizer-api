import com.google.gson.Gson;
import dao.Sql2oCharacterDao;
import dao.Sql2oClassDao;
import dao.Sql2oSpellDao;
import models.Class;
import models.Character;
import models.Spell;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import filters.CorsFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    private static boolean isProduction = false;

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        Sql2oSpellDao spellDao;
        Sql2oClassDao classDao;
        Sql2oCharacterDao characterDao;
        Connection con;
        Gson gson = new Gson();
        CorsFilter.apply();

        // Local Hosting settings
//        String connectionString = "jdbc:postgresql://localhost:5432/dndspells";
//        Sql2o sql2o = new Sql2o(connectionString, null, null);

        //Heroku deployment settings
        String connectionString = "jdbc:postgresql://ec2-54-204-2-26.compute-1.amazonaws.com:5432/d411v7fc01iqsf"; //!
        Sql2o sql2o = new Sql2o(connectionString, "qokmbwkfngyzps", "1ff8a264010c55cfaf51fb9d3577ea336d49e2b793120e298a339dda8ef74c44");

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

        //GET all classes
        get("/classes", "application/json", (request, response) -> {
            List<Class> allClasses = classDao.getAll();
            if (allClasses.size()<=0) {
                response.status(404);
                return gson.toJson(String.format("Sorry, there are no classes right now."));
            } else {
                response.status(200);
                return gson.toJson(allClasses);
            }
        });

        //GET all characters
        get("/characters", "application/json", (request, response) -> {
            System.out.println(request.body());
            List<Character> allCharacters = characterDao.getAll();
            if (allCharacters.size()<=0) {
                response.status(404);
                return gson.toJson(String.format("Sorry, there are no characters right now."));
            } else {
                response.status(200);
                return gson.toJson(allCharacters);
            }
        });

        //GET spell details by id
        get("/spells/:spellId", "application/json", (request, response) -> {
            try {
                Integer spellId = Integer.parseInt(request.params("spellId"));
                Spell foundSpell = spellDao.findById(spellId);
                if (foundSpell != null) {
                    response.status(200);
                    return gson.toJson(foundSpell);
                } else {
                    response.status(404);
                    return gson.toJson(String.format("This spell does not exist."));
                }
            } catch (Error error) {
                System.out.println(error);
                response.status(400);
                return gson.toJson(String.format("Bad request."));
            }
        });

        //GET character details by id
        get("/characters/:characterId", "application/json", (request, response) -> {
            try {
                Integer characterId = Integer.parseInt(request.params("characterId"));
                Character foundCharacter = characterDao.findById(characterId);
                if (foundCharacter != null) {
                    response.status(200);
                    return gson.toJson(foundCharacter);
                } else {
                    response.status(404);
                    return gson.toJson(String.format("This character does not exist."));
                }
            } catch (Error error) {
                System.out.println(error);
                response.status(400);
                return gson.toJson(String.format("Bad request."));
            }
        });

        //GET class details by id
        get("/classes/:classId", "application/json", (request, response) -> {
            try {
                Integer classId = Integer.parseInt(request.params("classId"));
                Class foundClass = classDao.findById(classId);
                if (foundClass != null) {
                    response.status(200);
                    return gson.toJson(foundClass);
                } else {
                    response.status(404);
                    return gson.toJson(String.format("This class does not exist."));
                }
            } catch (Error error) {
                System.out.println(error);
                response.status(400);
                return gson.toJson(String.format("Bad request."));
            }
        });

        //GET all spells for a class
        get("/classes/:classId/spells", "application/json", (request, response) -> {
            Integer classId = Integer.parseInt(request.params("classId"));
            if (classDao.findById(classId) == null) {
                response.status(400);
                return gson.toJson(String.format("This class does not exist."));
            }
            List<Spell> spells = spellDao.getAllSpellsOfAClass(classId);
            if (spells.size()<=0) {
                response.status(404);
                return gson.toJson(String.format("This class has no spells assigned to it."));
            } else {
                response.status(200);
                return gson.toJson(spells);
            }
        });

        //POST new character
        post("/characters/add", "application/json", (request, response) -> {
            try {
                System.out.println(request.body());
                Character newCharacter = gson.fromJson(request.body(), Character.class);
                characterDao.add(newCharacter);
                Character characterAdded = characterDao.findById(newCharacter.getId());
                response.status(201);
                response.type("application/json");
                return null;
//                return gson.toJson(characterAdded);
            } catch(Error error) {
                response.status(400);
                System.out.println(error);
                return gson.toJson(String.format("Sorry, your submission was not able to be processed"));
            }
        });

        //POST new class
        post("/classes/add", "application/json", (request, response) -> {
            try {
                Class newClass = gson.fromJson(request.body(), Class.class);
                classDao.add(newClass);
                Class classAdded = classDao.findById(newClass.getId());
                response.status(201);
                response.type("application/json");
                return gson.toJson(classAdded);
            } catch(Error error) {
                response.status(400);
                System.out.println(error);
                return gson.toJson(String.format("Sorry, your submission was not able to be processed"));
            }
        });

        //POST new spell
        post("/spells/add", "application/json", (request, response) -> {
            try {
                Spell spell = gson.fromJson(request.body(), Spell.class);
                spellDao.add(spell);
                Spell spellAdded = spellDao.findById(spell.getId());
                response.status(201);
                response.type("application/json");
                return gson.toJson(spellAdded);
            } catch(Error error) {
                response.status(400);
                System.out.println(error);
                return gson.toJson(String.format("Sorry, your submission was not able to be processed"));
            }
        });

        //POST assign spell to a class
        post("/classes/:classId/spells/:spellId/add", "application/json", (request, response) -> {
            Integer classId = Integer.parseInt(request.params("classId"));
            if (classDao.findById(classId) == null) {
                response.status(400);
                return gson.toJson(String.format("This class does not exist. Request denied."));
            }
            Integer spellId = Integer.parseInt(request.params("spellId"));
            if (spellDao.findById(spellId) == null){
                response.status(400);
                return gson.toJson(String.format("This spell does not exist. Request denied."));
            }
            spellDao.assignSpellToClass(spellId, classId);
            response.status(201);
            return gson.toJson(String.format("Success, spell has been assigned to class."));
        });

        //POST assign class to a character
        post("/characters/:characterId/classes/:classId/add", "application/json", (request, response) -> {
            Integer characterId = Integer.parseInt(request.params("characterId"));
            if (characterDao.findById(characterId) == null) {
                response.status(400);
                return gson.toJson(String.format("This character does not exist. Request denied."));
            }
            Integer classId = Integer.parseInt(request.params("classId"));
            Class classCheck = classDao.findById(classId);
            if (classCheck == null) {
                response.status(400);
                return gson.toJson(String.format("This class does not exist. Request denied."));
            }
            HashMap<String, Object> classIdUpdate = new HashMap<>();
            classIdUpdate.put("classId", classId);
            characterDao.update(classIdUpdate, characterId);
            Character foundCharacter = characterDao.findById(characterId);
            response.status(201);
            return gson.toJson(foundCharacter);
        });

//        UPDATE character by id
        put("/characters/:characterId/update", "application/json", (request, response) -> {
            Integer characterId = Integer.parseInt(request.params("characterId"));
            if (characterDao.findById(characterId) == null) {
                response.status(400);
                return gson.toJson(String.format("This character does not exist. Request denied."));
            }
            try {
                HashMap<String, Object> propertiesToUpdate = gson.fromJson(request.body(), HashMap.class);
                response.status(201);
                characterDao.update(propertiesToUpdate, characterId);
                return gson.toJson(characterDao.findById(characterId));

            } catch (Error error) {
                System.out.println(error);
                response.status(400);
                return gson.toJson(String.format("This is a bad request."));
            }
        });

//        //UPDATE class by id
        put("/classes/:classId/update", "application/json", (request, response) -> {
            Integer classId = Integer.parseInt(request.params("classId"));
            if (classDao.findById(classId) == null) {
                response.status(400);
                return gson.toJson(String.format("This class does not exist. Request denied."));
            }
            try {
                HashMap<String, Object> propertiesToUpdate = gson.fromJson(request.body(), HashMap.class);
                classDao.update(propertiesToUpdate, classId);
                response.status(201);
                return gson.toJson(classDao.findById(classId));

            } catch (Error error) {
                System.out.println(error);
                response.status(400);
                return gson.toJson(String.format("This is a bad request."));
            }
        });
//
//        //UPDATE spell by id
        put("/spells/:spellId/update", "application/json", (request, response) -> {
            Integer spellId = Integer.parseInt(request.params("spellId"));
            if (spellDao.findById(spellId) == null) {
                response.status(400);
                return gson.toJson(String.format("This spell does not exist. Request denied."));
            }
            try {
                HashMap<String, Object> propertiesToUpdate = gson.fromJson(request.body(), HashMap.class);
                response.status(201);
                spellDao.update(propertiesToUpdate, spellId);
                return gson.toJson(spellDao.findById(spellId));

            } catch (Error error) {
                System.out.println(error);
                response.status(400);
                return gson.toJson(String.format("This is a bad request."));
            }
        });
//
        //DELETE character by id
        delete("/characters/:characterId", "application/json", (request, response) -> {
            Integer characterId = Integer.parseInt(request.params("characterId"));
            characterDao.deleteById(characterId);
            response.status(200);
            return gson.toJson(String.format("Character deleted"));
        });

        //DELETE class by id
        delete("/classes/:classId", "application/json", (request, response) -> {
            Integer classId = Integer.parseInt(request.params("classId"));
            classDao.deleteById(classId);
            response.status(200);
            return gson.toJson(String.format("Class deleted"));
        });

        //DELETE spell by id
        delete("/spells/:spellId", "application/json", (request, response) -> {
            Integer spellId = Integer.parseInt(request.params("spellId"));
            spellDao.deleteById(spellId);
            response.status(200);
            return gson.toJson(String.format("Spell deleted"));
        });

        //GET/DELETE character by id
        get("/characters/:characterId/delete", "application/json", (request, response) -> {
            Integer characterId = Integer.parseInt(request.params("characterId"));
            characterDao.deleteById(characterId);
            response.status(200);
            return gson.toJson(String.format("Character deleted"));
        });

        //GET/DELETE class by id
        get("/classes/:classId/delete", "application/json", (request, response) -> {
            Integer classId = Integer.parseInt(request.params("classId"));
            classDao.deleteById(classId);
            response.status(200);
            return gson.toJson(String.format("Class deleted"));
        });

        //GET/DELETE spell by id
        get("/spells/:spellId/delete", "application/json", (request, response) -> {
            Integer spellId = Integer.parseInt(request.params("spellId"));
            spellDao.deleteById(spellId);
            response.status(200);
            return gson.toJson(String.format("Spell deleted"));
        });

        //Handling OPTIONS preflight?
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request
                    .headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers",
                        accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request
                    .headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods",
                        accessControlRequestMethod);
            }
            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        after((request, response) -> {
            response.type("application/json");
        });
    }
}