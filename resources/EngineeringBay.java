package processor;

import model.ColonyTask;
import resources.ResourceManager;

public class EngineeringBay implements IProcessor {
    @Override
    public boolean canProcess(ColonyTask task) { return task.getTaskType().equals("ENGINEERING"); }

    @Override
    public String processTask(ColonyTask task, ResourceManager rm) {
        if (task.performTask(rm)) return "[Eng Bay] SUCCESS: Repaired " + task.getName();
        return "[Eng Bay] FAILED: Not enough parts for " + task.getName();
    }
}