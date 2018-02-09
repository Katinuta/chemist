package by.teplouhova.chemist.command;

import by.teplouhova.chemist.command.client.*;
import by.teplouhova.chemist.command.common.*;
import by.teplouhova.chemist.command.doctor.*;
import by.teplouhova.chemist.command.pharmacist.*;
import by.teplouhova.chemist.command.user.*;
import by.teplouhova.chemist.service.*;

public enum CommandType {
    SIGN_IN(new SignInCommand(new UserService())),
    SIGN_OUT(new SignOutCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    TO_SIGN_UP(new ToSingUpPageCommand(new UserService())),
    SIGN_UP(new SignUpCommand(new UserService())),
    FIND_MEDICINE(new FindMedicineCommand(new MedicineService())),
    SHOW_CLIENT_PRESCRIPTIONS(new ShowClientPrescriptionsCommand(new ClientService())),
    SHOW_MEDICINE_BY_PAGE(new ShowMedicinesByPageCommand(new MedicineService())),
    NEXT_PAGE(new NextPageCommand()),
    TO_CART(new ToCartPageCommand(new MedicineService())),
    SHOW_DOCTOR_PRESCRIPTIONS(new ShowDoctorPrescriptionsCommand(new PrescriptionService())),
    BUY(new BuyCommand(new ClientService(), new OrderService(), new MedicineService(), new PrescriptionService())),
    TO_NEW_MEDICINE(new ToNewMedicinePageCommand()),
    TO_EDIT_MEDICINE(new ToEditMedicinePageCommand(new MedicineService())),
    DELETE_MEDICINE(new DeleteMedicineCommand(new MedicineService())),
    UPDATE_MEDICINE(new UpdateMedicineCommand(new MedicineService())),
    ADD_MEDICINE(new AddMedicineCommand(new MedicineService())),
    SHOW_ALL_ORDERS(new ShowAllOrdersCommand(new ClientService())),
    SHOW_PRESCRIPTION_INFO(new ShowPrescripInfoCommand(new PrescriptionService())),
    EXTEND_PRESCRIPTION(new ExtendPrescriptionCommand(new PrescriptionService())),
    EXTEND_PRESCRIPTION_DETAIL(new ExtendPrescripDetailCommand( new PrescripDetailService())),
    APPROVE_EXTENDING_PRESCRIPTION(new ApproveExtendingPrescriptionCommand(new PrescriptionService())),
    SHOW_ORDER_INFO(new ShowOrderInfoCommand(new OrderService())),
    SHOW_PRESCRIPTIONS_TO_EXTEND(new ShowRrescriptonsToExtendCommand(new PrescriptionService())),
    SHOW_ALL_CLIENTS(new ShowAllClientsCommand(new UserService())),
    TO_NEW_PRESCRIPTION(new ToNewPrescriptionPageCommand(new UserService())),
    TO_EDIT_PASSWORD(new ToEditPasswordPageCommand()),
    UPDATE_PASSWORD(new UpdatePasswordCommand(new UserService())),
    CREATE_PRESCRIPTION(new CreatePrescriptionCommand(new PrescriptionService(), new UserService())),
    TO_SIGN_IN(new ToSignInPageCommand()),
    EMPTY(new EmptyCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return this.command;
    }
}
