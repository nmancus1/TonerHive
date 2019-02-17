package Tests; /**
 * This class tests the adminbank and admin objects, creating admin objects, then adding them to the
 * adminbank, then writing the admin objects to the admin.byt file.
 */

import Objects.Admin;
import Objects.AdminBank;

import java.io.EOFException;
import java.io.IOException;

public class AdminTest {

    public static void main(String[] args) {

        //Create new admin bank
        AdminBank adminBank = new AdminBank();

        //Create admins
        Admin admin = new Admin("Admin", "tonerHiveBot@gmail.com", "password", 1234);
        //Admin david = new Admin("David", "dmatimu@dtcc.edu", "password", 2345);
        //Admin johne = new Admin("John E", "jescoba1@dtcc.edu", "password", 3465);
        //Admin johnb = new Admin("John B", "jbathan@dtcc.edu", "password", 4567);

        //Add admins to admin bank
        adminBank.add(admin);
        //adminBank.add(david);
        //adminBank.add(johne);
        //adminBank.add(johnb);

        //Load admin file from admins.byt
        try {
            adminBank.loadAdminFile();
        } catch (EOFException e) {
            System.out.println("Reached end of file");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Try out adding more admins, to write to file
        //Admin asdf = new Admin("fgh", "nmancus1@dtcc.edu", "blahblah", 1234);
        //Admin dgf = new Admin("fgh", "dmatimu@dtcc.edu", "password", 2345);
        //Admin dfg = new Admin("fgh E", "jescoba1@dtcc.edu", "asdfASDF", 3465);
        //Admin fgh = new Admin("fgh B", "jbathan@dtcc.edu", "passsswerd", 4567);

        //adminBank.add(asdf);
        //adminBank.add(dgf);
        //adminBank.add(dfg);
        //adminBank.add(fgh);

        //Write adminbank to file
        try {

            adminBank.toAdminFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
