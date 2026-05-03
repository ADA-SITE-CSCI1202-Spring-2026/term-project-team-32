package processor;

import model.ColonyTask;
import resources.ResourceManager;

public interface IProcessor {
    boolean canProcess(ColonyTask task);
    String processTask(ColonyTask task, ResourceManager rm);
}
    