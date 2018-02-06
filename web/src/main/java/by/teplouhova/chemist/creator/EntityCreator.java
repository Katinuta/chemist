package by.teplouhova.chemist.creator;

import by.teplouhova.chemist.entity.Entity;
import by.teplouhova.chemist.validator.FieldName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class EntityCreator {
    private static final Logger LOGGER = LogManager.getLogger();

    private Entity entity;

    public Entity create(HashMap<String,String> entityParams){

        Set<Map.Entry<String, String>> keySet = entityParams.entrySet();
        keySet.stream().filter(entry -> entry.getValue()!=null).forEach(entry -> {
            String current=entry.getKey();
            try{
                FieldName field=FieldName.valueOf(current.toUpperCase());
                fillField(field,entry.getValue());
            }catch (IllegalArgumentException e){
                LOGGER.info("Parameter "+current +" is not field  to " + getClass().getName());
            }
        });
        return entity;
    }

    protected abstract void fillField(FieldName name,String value );

}
