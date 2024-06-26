package dev.vibatista.literarybookstore.util;

import java.util.UUID;

public class UUIDUtil {

    // É static porque não depende do estado da classe, ele funciona de um jeito, independente de qualquer coisa
    public static boolean isValidUUID(String uuid){

        if(uuid == null|| uuid.isEmpty())
            return false;

        try{
            UUID idConverted = UUID.fromString(uuid);
            return true;
        }
        catch (IllegalArgumentException ex){
            return false;
        }
    }


}
