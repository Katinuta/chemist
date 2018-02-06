package by.teplouhova.chemist.creator;

import by.teplouhova.chemist.entity.impl.Medicine;

import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        HashMap<String,String> params=new HashMap<>();
        params.put("medicine_id","1");
        Medicine medicine= (Medicine) new NewMedicineCreator().create(params);
        System.out.println(medicine);
    }
}
