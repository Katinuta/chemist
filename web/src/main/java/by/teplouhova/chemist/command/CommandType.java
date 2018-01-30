package by.teplouhova.chemist.command;

import by.teplouhova.chemist.service.*;

public enum CommandType {
    LOGIN(new LoginCommand(new UserService())),
    LOGOUT(new LogoutCommand()),
    LOCALE(new LocaleCommand()),
    REGISTER(new RegisterCommand(new UserService())),
    CREATEUSER(new CreateUserCommand(new UserService())),
    FINDMEDICINE(new FindMedicineCommand(new MedicineService())),
    ALLCLIENTPRESCRIPTION(new ClientPrescriptionCommand(new ClientService())),
    ALLMEDICINE(new FindAllMedicinesCommand(new MedicineService())),
    NEXTPAGE(new NextPageCommand()),
    OPENCART(new OpenCartCommand(new MedicineService())),
    DOCTORPRESCRIPTION(new DoctorPrescriptionCommand(new PrescriptionService())),
    BUY(new BuyCommand(new UserService(), new OrderService(), new MedicineService(), new PrescriptionService())),
    NEWMEDICINE(new NewMedicineCommand()),
    EDITMEDICINE(new EditMedicineCommand(new MedicineService())),
    DELETEMEDICINE(new DeleteMedicineCommand(new MedicineService())),
    UPDATEMEDICINE(new UpdateMedicineCommand(new MedicineService())),
    CREATEMEDICINE(new CreateMedicineCommand(new MedicineService())),
    CLIENTPURCHASES(new ClientPurchasesCommand(new OrderService())),
    OPENPRESCRIPTION(new OpenPrescripCommand(new PrescriptionService(), new ClientService())),
    EXTENDPRESCRIPTION(new ExtendPrescriptionCommand(new PrescriptionService())),
    EXTENDPRESCRIPDETAIL(new ExtendPrescripDetailCommand(new ClientService(), new PrescripDetailService(),
            new PrescriptionService())),
    APPROVEEXTENDPRESCRIP(new ApproveExtendPrescripCommand(new PrescriptionService())),
    ORDERDETAIL(new OrderDetailCommand(new OrderService())),
    PRESCRIPTOEXTEND(new FindDoctorPrescripToExtandCommand(new PrescriptionService())),
    ALLCLIENTS(new FindAllClientsCommand(new UserService())),
    NEWPRESCRIPTION(new NewPrescriptionCommand(new UserService())),
    EDITPASSWORD(new EditPasswordCommand(new UserService())),
    UPDATEPASSWORD(new UpdatePasswordCommand(new UserService())),
    CREATEPRESCRIPTION(new CreatePrescriptionCommand(new PrescriptionService(), new UserService())),
    SIGNIN(new SignInCommand()),
    EMPTY(new EmptyCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return this.command;
    }
}
