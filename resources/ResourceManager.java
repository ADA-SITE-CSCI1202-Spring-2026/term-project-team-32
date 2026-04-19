package resources;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
    private Map<Resource, Integer> inventory;

    public ResourceManager() {
        inventory = new HashMap<>();
        inventory.put(Resource.OXYGEN, 100);
        inventory.put(Resource.RATIONS, 50);
        inventory.put(Resource.SPARE_PARTS, 30);
        inventory.put(Resource.CREDITS, 500); // Starting money for the shop
    }

    public int getAmount(Resource r) {
        return inventory.getOrDefault(r, 0);
    }

    public void setAmount(Resource r, int amount) {
        // Prevent resources from dropping below 0
        inventory.put(r, Math.max(0, amount));
    }

    // Used by the Shop dropdown
    public boolean purchase(Resource itemType, int quantity, int cost) {
        int currentCredits = getAmount(Resource.CREDITS);
        if (currentCredits >= cost) {
            setAmount(Resource.CREDITS, currentCredits - cost); 
            setAmount(itemType, getAmount(itemType) + quantity); 
            return true; 
        }
        return false; 
    }
}