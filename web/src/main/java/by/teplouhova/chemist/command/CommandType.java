package by.teplouhova.chemist.command;

import by.teplouhova.chemist.service.*;

public enum CommandType {
    LOGIN(new LoginCommand( new UserService())),
    LOGOUT(new LogoutCommand(new UserService())),
    LOCALE(new LocaleCommand()),
    REGISTER(new RegisterCommand(new UserService())),
    CREATEUSER(new CreateUserCommand(new UserService())),
    FINDMEDICINE(new FindMedicineCommand(new MedicineService())),
    ALLCLIENTPRESCRIPTION(new ClientPrescriptionCommand(new ClientService())),
    ALLMEDICINE(new FindAllMedicinesCommand(new MedicineService())),
    NEXTPAGE(new NextPage()),
    OPENCART(new OpenCartCommand()),
DOCTORPRESCRIPTION(new DoctorPrescriptionCommand(new PrescriptionService())),
    BUY(new BuyCommand(new OrderService(),new MedicineService(),new PrescriptionService())),
    NEWMEDICINE(new NewMedicineCommand()),
    EDITMEDICINE(new EditMedicineCommand(new MedicineService())),
    DELETEMEDICINE(new DeleteMedicineCommand(new MedicineService())),
    UPDATEMEDICINE(new UpdateMedicineCommand(new MedicineService())),
    CREATEMEDICINE(new CreateMedicineCommand(new MedicineService())),
    CLIENTPURCHASES(new ClientPurchasesCommand(new OrderService())),
    OPENPRESCRIPTION(new OpenPrescripCommand(new PrescriptionService(),new ClientService())),
    EXTENDPRESCRIPTION(new ExtendPrescriptionCommand(new PrescriptionService(),new ClientService())),
    EXTENDPRESCRIPDETAIL(new ExtendPrescripDetailCommand(new ClientService(), new PrescripDetailService(),
            new PrescriptionService())),
    APPROVEEXTENDPRESCRIP(new ApproveExtendPrescripCommand(new PrescriptionService()))
    ;

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return this.command;
    }
}
