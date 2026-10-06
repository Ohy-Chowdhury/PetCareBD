package petcare.app;

import petcare.service.PetCareSystem;
import petcare.ui.MainFrame;

public class Main {

    public static void main(String[] args) {

        PetCareSystem system = new PetCareSystem();

        new MainFrame(system);

    }

}