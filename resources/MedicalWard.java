package processor;

import model.ColonyTask;
import resources.ResourceManager;

public class MedicalWard implements IProcessor {
    @Override
    public boolean canProcess(ColonyTask task) { return task.getTaskType().equals("LIFE_SUPPORT"); }

    @Override
    public String processTask(ColonyTask task, ResourceManager rm) {
        if (task.performTask(rm)) return "[Med Ward] SUCCESS: Stabilized " + task.getName();
        return "[Med Ward] FAILED: Need parts & oxygen for " + task.getName();
    }
}