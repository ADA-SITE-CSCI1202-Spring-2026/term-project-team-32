package processor;

import model.ColonyTask;
import resources.ResourceManager;

public class ScienceLab implements IProcessor {
    @Override
    public boolean canProcess(ColonyTask task) { return task.getTaskType().equals("~Research Task~"); }

    @Override
    public String processTask(ColonyTask task, ResourceManager rm) {
        if (task.performTask(rm)) return "[Science Lab] SUCCESS: Completed " + task.getName() + " (+500 Cr)";
        return "[Science Lab] FAILED: Missing equipment for " + task.getName();
    }
}
