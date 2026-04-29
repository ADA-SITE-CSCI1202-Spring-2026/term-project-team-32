package model;

import resources.Resource;
import resources.ResourceManager;

public class EngineeringTask extends ColonyTask {
    public EngineeringTask(String name, int reqParts) {
        super(name, reqParts, 110, 4, 55);
    }
    
@Override
public String getType(){ return "~Engineering Task~";}

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

