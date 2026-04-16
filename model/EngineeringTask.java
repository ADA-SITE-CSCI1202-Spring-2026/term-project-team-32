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
    if ()

}







}

