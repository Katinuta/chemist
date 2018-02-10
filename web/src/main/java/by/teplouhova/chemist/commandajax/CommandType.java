package by.teplouhova.chemist.commandajax;

import by.teplouhova.chemist.service.DosageService;
import by.teplouhova.chemist.service.MedicineService;
import by.teplouhova.chemist.service.ProducerService;
import by.teplouhova.chemist.service.ReleaseFormService;

/**
 * The Enum CommandType.
 */
public enum CommandType {

    /**
     * The add product to cart.
     */
    ADD_PRODUCT_TO_CART(new AddProductToCartCommand(new MedicineService())),

    /**
     * The increase product amount cart.
     */
    INCREASE_PRODUCT_AMOUNT_CART(new IncreaseProductAmountCartCommand(new MedicineService())),

    /**
     * The reduce product amount cart.
     */
    REDUCE_PRODUCT_AMOUNT_CART(new ReduceProductAmountCartCommand(new MedicineService())),

    /**
     * The delete product cart.
     */
    DELETE_PRODUCT_CART(new DeleteProductCartCommand()),

    /**
     * The find medicine details.
     */
    FIND_MEDICINE_DETAILS(new FindMedicineDetailsCommand(new ReleaseFormService(), new ProducerService(), new DosageService(),
            new MedicineService())),

    /**
     * The get cart products id.
     */
    GET_CART_PRODUCTS_ID(new GetCartProductsIdCommand()),

    /**
     * The show medicines by prescrip.
     */
    SHOW_MEDICINES_BY_PRESCRIP(new ShowMedicinesByPrescripCommand(new MedicineService())),

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
