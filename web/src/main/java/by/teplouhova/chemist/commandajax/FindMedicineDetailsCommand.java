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

/**
 * The Class FindMedicineDetailsCommand.
 */
public class FindMedicineDetailsCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The Constant ATTR_FORMS.
     */
    private static final String ATTR_FORMS = "forms";

    /**
     * The Constant ATTR_PRODUCERS.
     */
    private static final String ATTR_PRODUCERS = "producers";

    /**
     * The Constant ATTR_UNITS_IN_PACK.
     */
    private static final String ATTR_UNITS_IN_PACK = "unitsInPack";

    /**
     * The Constant ATTR_DOSAGE_UNITS.
     */
    private static final String ATTR_DOSAGE_UNITS = "dosageUnits";

    /**
     * The Constant ATTR_ANALOGS.
     */
    private static final String ATTR_ANALOGS = "analogs";

    /**
     * The release form service.
     */
    private ReleaseFormService releaseFormService;

    /**
     * The producer service.
     */
    private ProducerService producerService;

    /**
     * The dosage service.
     */
    private DosageService dosageService;

    /**
     * The medicine service.
     */
    private MedicineService medicineService;

    /**
     * Instantiates a new find medicine details command.
     *
     * @param releaseFormService the release form service
     * @param producerService    the producer service
     * @param dosageService      the dosage service
     * @param medicineService    the medicine service
     */
    public FindMedicineDetailsCommand(ReleaseFormService releaseFormService, ProducerService producerService,
                                      DosageService dosageService, MedicineService medicineService) {
        this.releaseFormService = releaseFormService;
        this.producerService = producerService;
        this.dosageService = dosageService;
        this.medicineService = medicineService;
    }

    /**
     * Execute.
     *
     * @param content the content
     * @return the JSON object
     */
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
