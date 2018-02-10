package by.teplouhova.chemist.command;

import by.teplouhova.chemist.command.client.*;
import by.teplouhova.chemist.command.common.*;
import by.teplouhova.chemist.command.doctor.*;
import by.teplouhova.chemist.command.pharmacist.*;
import by.teplouhova.chemist.command.user.*;
import by.teplouhova.chemist.service.*;

/**
 * The Enum CommandType.
 */
public enum CommandType {

    /**
     * The sign in.
     */
    SIGN_IN(new SignInCommand(new UserService())),

    /**
     * The sign out.
     */
    SIGN_OUT(new SignOutCommand()),

    /**
     * The change locale.
     */
    CHANGE_LOCALE(new ChangeLocaleCommand()),

    /**
     * The to sign up.
     */
    TO_SIGN_UP(new ToSingUpPageCommand()),

    /**
     * The sign up.
     */
    SIGN_UP(new SignUpCommand(new UserService())),

    /**
     * The find medicine.
     */
    FIND_MEDICINE(new FindMedicineCommand(new MedicineService())),

    /**
     * The show client prescriptions.
     */
    SHOW_CLIENT_PRESCRIPTIONS(new ShowClientPrescriptionsCommand(new ClientService())),

    /**
     * The show medicine by page.
     */
    SHOW_MEDICINE_BY_PAGE(new ShowMedicinesByPageCommand(new MedicineService())),

    /**
     * The next page.
     */
    NEXT_PAGE(new NextPageCommand()),

    /**
     * The to cart.
     */
    TO_CART(new ToCartPageCommand(new MedicineService())),

    /**
     * The show doctor prescriptions.
     */
    SHOW_DOCTOR_PRESCRIPTIONS(new ShowDoctorPrescriptionsCommand(new PrescriptionService())),

    /**
     * The buy.
     */
    BUY(new BuyCommand(new ClientService(), new OrderService(), new MedicineService(), new PrescriptionService())),

    /**
     * The to new medicine.
     */
    TO_NEW_MEDICINE(new ToNewMedicinePageCommand()),

    /**
     * The to edit medicine.
     */
    TO_EDIT_MEDICINE(new ToEditMedicinePageCommand(new MedicineService())),

    /**
     * The delete medicine.
     */
    DELETE_MEDICINE(new DeleteMedicineCommand(new MedicineService())),

    /**
     * The update medicine.
     */
    UPDATE_MEDICINE(new UpdateMedicineCommand(new MedicineService())),

    /**
     * The add medicine.
     */
    ADD_MEDICINE(new AddMedicineCommand(new MedicineService())),

    /**
     * The show all orders.
     */
    SHOW_ALL_ORDERS(new ShowAllOrdersCommand(new ClientService())),

    /**
     * The show prescription info.
     */
    SHOW_PRESCRIPTION_INFO(new ShowPrescripInfoCommand(new PrescriptionService())),

    /**
     * The extend prescription.
     */
    EXTEND_PRESCRIPTION(new ExtendPrescriptionCommand(new PrescriptionService())),

    /**
     * The extend prescription detail.
     */
    EXTEND_PRESCRIPTION_DETAIL(new ExtendPrescripDetailCommand(new PrescripDetailService())),

    /**
     * The approve extending prescription.
     */
    APPROVE_EXTENDING_PRESCRIPTION(new ApproveExtendingPrescriptionCommand(new PrescriptionService())),

    /**
     * The show order info.
     */
    SHOW_ORDER_INFO(new ShowOrderInfoCommand(new OrderService())),

    /**
     * The show prescriptions to extend.
     */
    SHOW_PRESCRIPTIONS_TO_EXTEND(new ShowRrescriptonsToExtendCommand(new PrescriptionService())),

    /**
     * The show all clients.
     */
    SHOW_ALL_CLIENTS(new ShowAllClientsCommand(new UserService())),

    /**
     * The to new prescription.
     */
    TO_NEW_PRESCRIPTION(new ToNewPrescriptionPageCommand(new UserService())),

    /**
     * The to edit password.
     */
    TO_EDIT_PASSWORD(new ToEditPasswordPageCommand()),

    /**
     * The update password.
     */
    UPDATE_PASSWORD(new UpdatePasswordCommand(new UserService())),

    /**
     * The create prescription.
     */
    CREATE_PRESCRIPTION(new CreatePrescriptionCommand(new PrescriptionService(), new UserService())),

    /**
     * The to sign in.
     */
    TO_SIGN_IN(new ToSignInPageCommand()),

    /**
     * The empty.
     */
    EMPTY(new EmptyCommand());

    /**
     * The command.
     */
    private Command command;

    /**
     * Instantiates a new command type.
     *
     * @param command the command
     */
    CommandType(Command command) {
        this.command = command;
    }

    /**
     * Gets the command.
     *
     * @return the command
     */
    public Command getCommand() {
        return this.command;
    }
}
