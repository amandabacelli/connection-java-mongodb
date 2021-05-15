import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of season: ");
        int seasonId = sc.nextInt();

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://dbAJ:admin@friends.l4twe.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("dump");
        MongoCollection<Document> collection = database.getCollection("samples_friends");

        printDocumentsWithPropertiesNameAndSeason(collection, seasonId);

        sc.close();
        mongoClient.close();

    }

    private static void printDocumentsWithPropertiesNameAndSeason(MongoCollection<Document> collection, int seasonId) {
        Iterable<Document> documents = collection.find(new Document("season", seasonId));
        for (Document doc : documents) {
            System.out.println(doc.get("name") + " " + doc.get("season"));
        }
        System.out.println("\nDeu bom ｡ﾟ(ﾟ^Ｏ^ﾟ)ﾟ｡");
    }
}
