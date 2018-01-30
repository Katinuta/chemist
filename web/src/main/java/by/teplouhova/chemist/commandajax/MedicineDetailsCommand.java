package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Producer;
import by.teplouhova.chemist.entity.impl.ReleaseForm;
import by.teplouhova.chemist.service.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class MedicineDetailsCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private ReleaseFormService releaseFormService;
    private ProducerService producerService;
    private DosageService dosageService;
    private MedicineService medicineService;

    public MedicineDetailsCommand(ReleaseFormService releaseFormService, ProducerService producerService, DosageService dosageService, MedicineService medicineService) {
        this.releaseFormService = releaseFormService;
        this.producerService = producerService;
        this.dosageService = dosageService;
        this.medicineService = medicineService;
    }

    @Override
    public JSONObject execute(SessionRequestContent content) {
        JSONObject object = new JSONObject();

        try {
            ArrayList<ReleaseForm> releaseForm = releaseFormService.getReleaseForms();
            ArrayList<Producer> producers = producerService.getProdusers();
            ArrayList<String> dosageUnits = dosageService.getDosageUnits();
            ArrayList<String> unitsInPack = medicineService.getUnitsInPackage();
            HashSet<Long> setIds=medicineService.getMedicinesId();
            object.put("forms", releaseForm);
            object.put("producers", producers);
            object.put("dosageUnits", dosageUnits);
            object.put("unitsInPack", unitsInPack);
            object.put("analogs",setIds);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return object;
    }
}
