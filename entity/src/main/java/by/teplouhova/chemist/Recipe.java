package by.teplouhova.chemist;

import java.time.LocalDate;
import java.util.List;

public class Recipe extends Entity{
    private long recipeId;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private User client;
    private User doctor;
    private boolean isActive;

}
