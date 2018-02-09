package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.controller.SessionRequestContent;
import by.teplouhova.chemist.entity.impl.Producer;
import by.teplouhova.chemist.entity.impl.ReleaseForm;
import by.teplouhova.chemist.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class FindMedicineDetailsCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ATTR_FORMS ="forms";
    private static final String ATTR_PRODUCERS ="producers";
    private static final String ATTR_UNITS_IN_PACK="unitsInPack";
    private static final String ATTR_DOSAGE_UNITS="dosageUnits";
    private static  final String ATTR_ANALOGS="analogs";

    private ReleaseFormService releaseFormService;
    private ProducerService producerService;
    private DosageService dosageService;
    private MedicineService medicineService;

    public FindMedicineDetailsCommand(ReleaseFormService releaseFormService, ProducerService producerService,
                                      DosageService dosageService, MedicineService medicineService) {
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
            HashSet<Long> setIds = medicineService.getMedicinesId();
            object.put(ATTR_FORMS, releaseForm);
            object.put(ATTR_PRODUCERS, producers);
            object.put(ATTR_DOSAGE_UNITS, dosageUnits);
            object.put(ATTR_UNITS_IN_PACK, unitsInPack);
            object.put(ATTR_ANALOGS, setIds);

        } catch (ServiceException e) {
            LOGGER.catching(e);
        }
        return object;
    }
}
