package persistence;
//this code will not work for now
//ResourceManager.java will be added later. 
//then the code will work

import resources.Resource;
import resources.ResourceManager;//coming soon...

import java.io.*;

public class SaveLoadService {
    private static final String FILE_NAME = "ares_save.txt";

    public static void saveGame(ResourceManager rm) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println(rm.getAmount(Resource.OXYGEN));
            writer.println(rm.getAmount(Resource.RATIONS));
            writer.println(rm.getAmount(Resource.SPARE_PARTS));
            writer.println(rm.getAmount(Resource.CREDITS));
        }
    }

    public static void loadGame(ResourceManager rm) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            rm.setAmount(Resource.OXYGEN, Integer.parseInt(reader.readLine()));
            rm.setAmount(Resource.RATIONS, Integer.parseInt(reader.readLine()));
            rm.setAmount(Resource.SPARE_PARTS, Integer.parseInt(reader.readLine()));
            rm.setAmount(Resource.CREDITS, Integer.parseInt(reader.readLine()));
        }
    }
}