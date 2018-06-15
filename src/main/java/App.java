import com.google.gson.Gson;
import dao.Sql2oCharacterDao;
import dao.Sql2oClassDao;
import dao.Sql2oSpellDao;
import models.Class;
import models.Character;
import models.Spell;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        Sql2oSpellDao spellDao;
        Sql2oClassDao classDao;
        Sql2oCharacterDao characterDao;
        Connection con;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:`/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        spellDao = new Sql2oSpellDao(sql2o);
        classDao = new Sql2oClassDao(sql2o);
        characterDao = new Sql2oCharacterDao(sql2o);
        con = sql2o.open();

        //GET all spells

        //GET all classes

        //GET all characters

        //GET spell details by id

        //GET character details by id

        //GET class details by id

        //GET all spells for a class

        //POST new character

        //POST new class

        //POST new spell

        //POST assign spell to a class

        //POST assign class to a character

        //UPDATE character by id

        //UPDATE class by id

        //UPDATE spell by id

        //DELETE character by id

        //DELETE class by id

        //DELETE spell by id
    }
}
