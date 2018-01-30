package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.service.*;

public enum CommandType {
    ADDPRODUCT(new AddProductCommand(new MedicineService())),
    INCREASEPRODUCT(new IncreaseProductCommand(new MedicineService())),
    REDUCEPRODUCT(new ReduceProductCommand(new MedicineService())),
    DELETEMEDICINECART(new DeleteMedicineCart()),
    BUYCOMMAND(new BuyCommand(new UserService())),
    RELEASEFORM(new MedicineDetailsCommand(new ReleaseFormService(),new ProducerService(),new DosageService(),
            new MedicineService())),
    GETCARTIDPRODUCTS(new GetCartIdProductsCommand()),
    MEDICINESBYPRESCRIP(new MedicinesByPrescripCommand(new MedicineService()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return this.command;
    }
}
