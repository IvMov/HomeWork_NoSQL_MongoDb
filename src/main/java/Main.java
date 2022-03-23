import com.github.javafaker.Faker;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    private static final String MONGO_URI = "mongodb+srv://Admin:Admin@cluster0.6uu9y.mongodb.net/Akademija?retryWrites=true&w=majority";

    public static void main(String[] args) throws IOException {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(MONGO_URI));

        MongoDatabase mongoDatabase = mongoClient.getDatabase("Exam");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("vigi_8");

//        create10students(mongoCollection); //create students

        //visus studentus su tam tikru vardu;
        Document documentOutput = mongoCollection.find(Filters.eq("student_name", "Dexter")).first();
        System.out.println(documentOutput.get("student_name") + " mark is: " + documentOutput.get("mark"));

        System.out.println("010101--------TARPAS---------010101------TARPELIS----------010101");

        MongoCursor<Document> documents = mongoCollection.find().cursor();
        ArrayList<String> passExam = new ArrayList<>();
        ArrayList<String> notPassExam = new ArrayList<>();
        while (documents.hasNext()) {
            int i = Integer.parseInt((String) documents.next().get("mark"));
            if (i >= 40) {
                passExam.add(documents.next().get("student_name") + " have mark: " + i);
            } else if (i < 40) {
                notPassExam.add(documents.next().get("student_name") + " have mark: " + i);
            }
        }

        System.out.println("\nIslaiko examina:");//visus studentus kurie išlaikė egzaminą (rezultatas daugiau negu 4);
        for (String each : passExam
        ) {
            System.out.println(each);
        }

        System.out.println("\nNE Islaiko examina:");//visus studentus kurie neišlaikė egzamino (rezultatas mažiau negu 4);
        for (String each : notPassExam
        ) {
            System.out.println(each);
        }

    }

    private static void create10students(MongoCollection mongoCollection) {
        for (int i = 0; i < 10; i++) {
            Faker faker = new Faker();
            String name = faker.name().firstName();
            String examTheme = faker.pokemon().name();
            String mark = String.valueOf(faker.number().numberBetween(1, 100));
            Document document = new Document("student_name", name)
                    .append("examTheme", examTheme)
                    .append("mark", mark);

            mongoCollection.insertOne(document);
        }
    }


}
