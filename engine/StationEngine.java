package engine;

import model.*;
import processor.IProcessor;
import resources.Resource;
import resources.ResourceManager;

import javax.swing.Timer;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.function.Consumer;

public class StationEngine {
    // Core dependencies and state
    private ResourceManager resourceManager;
    private Queue<ColonyTask> taskQueue;
    private List<IProcessor> processors;
    
    // UI Callbacks
    private Consumer<String> uiLogger; 
    private Consumer<Void> uiUpdater;  
    
    // Utilities
    private Timer gameClock;
    private Random random = new Random();
    private final int MAX_QUEUE_SIZE = 5;

    public StationEngine(ResourceManager rm, List<IProcessor> processors) {
        this.resourceManager = rm;
        this.processors = processors;
        this.taskQueue = new LinkedList<>();
        
        // Timer is initialized but not started yet.
        // It points to a method we will build in the next form.
        gameClock = new Timer(5000, e -> generateRandomTask());
    }

    // --- SETUP & UTILITY METHODS ---

    public void setCallbacks(Consumer<String> logger, Consumer<Void> updater) {
        this.uiLogger = logger;
        this.uiUpdater = updater;
    }

    public Queue<ColonyTask> getTaskQueue() { return taskQueue; }
    public ResourceManager getResourceManager() { return resourceManager; }
    
    // Placeholder for the next stage
    private void generateRandomTask() {}
}