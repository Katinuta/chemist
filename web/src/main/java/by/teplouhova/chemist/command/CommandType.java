package by.teplouhova.chemist.command;

import by.teplouhova.chemist.service.ClientService;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.UserService;

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
    OPENBASKET(new OpenBasketCommand())
    ;

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return this.command;
    }
}
