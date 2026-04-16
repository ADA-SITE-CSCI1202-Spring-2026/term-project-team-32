package main;
import engine.GameEngine;

public class Main {
    public static void main(String args[]){
        GameEngine engine = new GameEngine();
        engine.start();
        while(true){
            engine.update();
        }
    }
}

