package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.service.*;

public enum CommandType {
    ADDPRODUCT(new AddProductCommand(new MedicineService())),
    INCREASEPRODUCT(new IncreaseProductCommand(new MedicineService())),
    REDUCEPRODUCT(new ReduceProductCommand()),
    DELETEMEDICINECART(new DeleteMedicineCart()),
    BUYCOMMAND(new BuyCommand(new UserService())),
    RELEASEFORM(new ReleaseFormCommand(new ReleaseFormService(),new ProducerService(),new DosageService(),
            new MedicineService())),
    GETCARTIDPRODUCTS(new GetCartIdProductsCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return this.command;
    }
}
