package dev.vibatista.literarybookstore.util;

import java.util.UUID;

public class UUIDUtil {

    // É static porque não depende do estado da classe, ele funciona de um jeito, independente de qualquer coisa
    public static boolean isValidUUID(UUID uuid){

        if(uuid == null)
            return false;

        try{
            String id = uuid.toString();
            UUID idConverted = UUID.fromString(id);
            return true;
        }
        catch (IllegalArgumentException ex){
            return false;
        }
    }


}
