package model;


import resources.Resource;
import resources.ResourceManager;

public class ResearchTask extends ColonyTask{
     public ResearchTask(String name, int reqParts) {
        super(name, reqParts, 500, 5, 0); 
    }

    @Override
    public String getTaskType() { return "~Research Task~"; }

    @Override
    public boolean performTask(ResourceManager rm) {
        if (rm.getAmount(Resource.SPARE_PARTS) >= this.reqParts) {
            rm.setAmount(Resource.SPARE_PARTS, rm.getAmount(Resource.SPARE_PARTS) - this.reqParts);
            rm.setAmount(Resource.CREDITS, rm.getAmount(Resource.CREDITS) + this.reward);
            return true;
        }
        return false;
    }
}
