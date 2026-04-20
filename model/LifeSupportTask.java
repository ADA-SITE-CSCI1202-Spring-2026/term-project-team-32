package model;

import resources.Resource;
import resources.ResourceManager;

public class LifeSupportTask extends ColonyTask {
    public LifeSupportTask(String name, int reqParts) {
        super(name, reqParts, 150, 2, 200);
    }
@Override
public String getType(){ return "~Life Support Task~";}

@Override
public boolean performTask(ResourceManager rm) {
    if (rm.getAmount(Resource.SPARE_PARTS) >= this.reqParts && rm.getAmount(Resource.OXYGEN) >= 10) {
        rm.setAmount(Resource.SPARE_PARTS, rm.getAmount(Resource.SPARE_PARTS) - this.reqParts);
        rm.setAmount(Resource.OXYGEN, rm.getAmount(Resource.OXYGEN) - 10);
        rm.setAmount(Resource.CREDITS, rm.getAmount(Resource.CREDITS) + this.reward);
        return true;
    }
    return false;   

} }
