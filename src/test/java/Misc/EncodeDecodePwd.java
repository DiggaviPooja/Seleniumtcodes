package Misc;

import java.util.Base64;

public class EncodeDecodePwd {

    public static void main(String[] args)
    {
        String str = "test123";

        byte[] encodedString = Base64.getEncoder().encode(str.getBytes());
        System.out.println("encoded string: "+new String(encodedString));

        byte[] decodedString =  Base64.getDecoder().decode(encodedString);
        System.out.println("decoded string: "+new String(decodedString));
    }
}
