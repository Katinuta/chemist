package by.teplouhova.chemist.dao;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MAin {
    public static void main(String [] args){
      //  ResourceBundle bundle=DatabaseConfigManager.getInstance().getBundle();
        //String t= DatabaseConfigManager.getInstance().getString("maxactive");
        BlockingQueue<String> list=new ArrayBlockingQueue<String>(15);
        list.offer("s");
        System.out.println(list.size());
    }
}
