package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.service.DosageService;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ProducerService;
import by.teplouhova.chemist.service.ReleaseFormService;

public enum CommandType {
    ADD_PRODUCT_TO_CART(new AddProductToCartCommand(new MedicineService())),
    INCREASE_PRODUCT_AMOUNT_CART(new IncreaseProductAmountCartCommand(new MedicineService())),
    REDUCE_PRODUCT_AMOUNT_CART(new ReduceProductAmountCartCommand(new MedicineService())),
    DELETE_PRODUCT_CART(new DeleteProductCartCommand()),
    FIND_MEDICINE_DETAILS(new FindMedicineDetailsCommand(new ReleaseFormService(),new ProducerService(),new DosageService(),
            new MedicineService())),
    GET_CART_PRODUCTS_ID(new GetCartProductsIdCommand()),
    SHOW_MEDICINES_BY_PRESCRIP(new ShowMedicinesByPrescripCommand(new MedicineService())),
    EMPTY(new EmptyCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand(){
        return this.command;
    }
}
