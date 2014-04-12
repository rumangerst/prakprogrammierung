/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caesar;

/**
 *
 * @author Ruman
 */
public class Caesar
{

    public static String verschluesseln(String text, int schluessel)
    {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < text.length(); i++)
        {
            char currentchar = Character.toUpperCase(text.charAt(i)); //Make char an upper character

            if (Character.isLetter(currentchar)) //If letter, modify it
            {
                currentchar += schluessel; //Add key to character value

                //Now check if its in A-Z and correct everything               
                while (currentchar > 'Z')
                {
                    currentchar -= 26; //Cycle backward
                }
                while (currentchar < 'A')
                {
                    currentchar += 26; //Cycle forward
                }
            }

            //Append to string builder
            output.append(currentchar);

        }

        return output.toString();
    }

    public static String entschluesseln(String text, int schluessel)
    {
        return Caesar.verschluesseln(text, -schluessel);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String input = "JDOOLHQ CHUIDHOOW LP JDQCHQ LQ GUHL WHLOH ZREHL GHQ HLQHQ GLH EHOJHU GHQ DQGHUHQ GLH DTXLWDQHU XQG GHQ GULWWHQ GLH LQ LKUHU VSUDFKH NHOWHQ XQG YRQ XQV JDOOLHU JHQDQQWHQ EHZRKQHQ";
        String decrypted = Caesar.entschluesseln(input, 3);
        String encrypted = Caesar.verschluesseln(decrypted, 3);

        System.out.println(decrypted);
        System.out.println(encrypted);
       
        for(int i = 0; i < input.length(); i++)
        {
            if(input.charAt(i) != encrypted.charAt(i))
            {
                System.out.println("Differ @ " + i);
            }
        }
        
        System.out.println("Input=>Decrypt=>Encrypt Verlust? " + (input.equals(encrypted) ? "Nein" : "Ja" ));
    }

}
