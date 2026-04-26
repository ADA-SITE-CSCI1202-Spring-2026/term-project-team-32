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
    private ResourceManager resourceManager;
    private Queue<ColonyTask> taskQueue;
    private List<IProcessor> processors;
    private Consumer<String> uiLogger; 
    private Consumer<Void> uiUpdater;  
    private Timer gameClock;
    private Random random = new Random();
    private final int MAX_QUEUE_SIZE = 5; 

    public StationEngine(ResourceManager rm, List<IProcessor> processors) {
        this.resourceManager = rm;
        this.processors = processors;
        this.taskQueue = new LinkedList<>();
        gameClock = new Timer(5000, e -> generateRandomTask());
    }

    public void setCallbacks(Consumer<String> logger, Consumer<Void> updater) {
        this.uiLogger = logger;
        this.uiUpdater = updater;
    }

    public void startEngine() { 
        gameClock.start(); 
    }

    private void generateRandomTask() {
        int rng = random.nextInt(3);
        int parts = random.nextInt(3) + 1;
        ColonyTask newTask;
        
        if (rng == 0) newTask = new EngineeringTask("Hull Breach Lvl " + parts, parts * 2);
        else if (rng == 1) newTask = new LifeSupportTask("Oxygen Leak Lvl " + parts, parts * 2);
        else newTask = new ResearchTask("Analyze Martian Soil", parts * 3);
        
        taskQueue.add(newTask);
        
        if (uiLogger != null) uiLogger.accept("ALERT: " + newTask.getName() + " [" + newTask.getTaskType() + "] added.");
        if (uiUpdater != null) uiUpdater.accept(null);
    }

    public Queue<ColonyTask> getTaskQueue() { return taskQueue; }
    public ResourceManager getResourceManager() { return resourceManager; }
}