/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class pkg2labopptask_4java {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for (int i = 1; i <= 20; i++) {
            
            if (i % 3 == 0) {
                continue;
            }

           
            if (i == 15) {
                break;
            }

            System.out.println(i);
        }
    }
}
    
    

