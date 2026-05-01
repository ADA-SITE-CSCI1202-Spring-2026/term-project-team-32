package main;

import engine.StationEngine;
import processor.EngineeringBay;
import processor.IProcessor;
import processor.MedicalWard;
import processor.ScienceLab;
import resources.ResourceManager;
import ui.MainDashboard;

import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.List;

public class AresBaseApp {
    public static void main(String[] args) {
        ResourceManager resourceManager = new ResourceManager();

   
        List<IProcessor> processors = new ArrayList<>();
        processors.add(new EngineeringBay());
        processors.add(new MedicalWard());
        processors.add(new ScienceLab()); 

        StationEngine engine = new StationEngine(resourceManager, processors);

        SwingUtilities.invokeLater(() -> {
            MainDashboard dashboard = new MainDashboard(engine);
            dashboard.setLocationRelativeTo(null); 
            dashboard.setVisible(true);
        });
    }
}