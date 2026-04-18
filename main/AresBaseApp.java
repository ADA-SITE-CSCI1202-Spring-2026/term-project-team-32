package main;

import engine.StationEngine;//will be added later
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import processor.EngineeringBay;//will be added later
import processor.MedicalWard;//will be added later
import processor.ScienceLab;// will be added later
import resources.ResourceManager;// will be added later
import ui.MainDashboard;

public class AresBaseApp {
   public AresBaseApp() {
   }

   public static void main(String[] var0) {
      ResourceManager var1 = new ResourceManager();
      ArrayList var2 = new ArrayList();
      var2.add(new EngineeringBay());
      var2.add(new MedicalWard());
      var2.add(new ScienceLab());
      StationEngine var3 = new StationEngine(var1, var2);
      SwingUtilities.invokeLater(() -> {
         MainDashboard var1 = new MainDashboard(var3);
         var1.setLocationRelativeTo((Component)null);
         var1.setVisible(true);
      });
   }
}

