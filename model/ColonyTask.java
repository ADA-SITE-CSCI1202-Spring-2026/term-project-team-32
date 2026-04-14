package model;


public abstract class ColonyTask {
    protected String name;
    protected int reqParts;
    protected int reward;
    protected int fixTime;
    protected int penalty; 

    public ColonyTask(String name, int reqParts, int reward, int fixTime, int penalty) {
        this.name = name;
        this.reqParts = reqParts;
        this.reward = reward;
        this.fixTime = fixTime;
        this.penalty = penalty;
    }

    public String getName(){
        return name;
    }
    public int getreqParts(){
        return reqParts;
    }
    public int getReward(){
        return reward;
    }
    public int getFixTime(){
        return fixTime;
    }
    public int getPenalty(){
        return penalty;
    }


}