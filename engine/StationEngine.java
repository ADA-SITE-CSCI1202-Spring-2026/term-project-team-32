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
    private final int MAX_QUEUE_SIZE = 5; // The limit before tasks fail

    public StationEngine(ResourceManager rm, List<IProcessor> processors) {
        this.resourceManager = rm;
        this.processors = processors;
        this.taskQueue = new LinkedList<>();
        
        // Generates a task every 5 seconds
        gameClock = new Timer(5000, e -> generateRandomTask());
    }

    public void setCallbacks(Consumer<String> logger, Consumer<Void> updater) {
        this.uiLogger = logger;
        this.uiUpdater = updater;
    }

    public void startEngine() { gameClock.start(); }

    private void generateRandomTask() {
        // Queue Overflow Logic (The Stick)
        if (taskQueue.size() >= MAX_QUEUE_SIZE) {
            ColonyTask failedTask = taskQueue.poll();
            resourceManager.setAmount(Resource.CREDITS, resourceManager.getAmount(Resource.CREDITS) - failedTask.getPenalty());
            
            if (uiLogger != null) {
                uiLogger.accept("CRITICAL FAILURE: Ignored " + failedTask.getName() + "!");
                uiLogger.accept("PENALTY APPLIED: Lost " + failedTask.getPenalty() + " Credits.");
            }
        }

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

    public void executeNextTask() {
        if (taskQueue.isEmpty()) {
            uiLogger.accept("Queue is empty. Station is secure.");
            return;
        }

        ColonyTask nextTask = taskQueue.peek();

        for (IProcessor processor : processors) {
            if (processor.canProcess(nextTask)) {
                String resultMessage = processor.processTask(nextTask, resourceManager);
                
                if (resultMessage.contains("SUCCESS")) {
                    taskQueue.poll(); // Remove from queue only if successful
                }
                
                uiLogger.accept(resultMessage);
                uiUpdater.accept(null);
                return;
            }
        }
    }

    public void buySupplies(Resource type) {
        int cost = 100; 
        int amount = 10; 

        if (resourceManager.purchase(type, amount, cost)) {
            if (uiLogger != null) uiLogger.accept("SHOP: Bought 10 " + type + " for 100 Credits.");
        } else {
            if (uiLogger != null) uiLogger.accept("SHOP ERROR: Insufficient credits.");
        }
        if (uiUpdater != null) uiUpdater.accept(null);
    }

    public Queue<ColonyTask> getTaskQueue() { return taskQueue; }
    public ResourceManager getResourceManager() { return resourceManager; }
}