package Objects; /**
 * <h1>AdminBank Data Structure</h1>
 * AdminBank is a data structure that stores and reads/writes admin objects to a file.
 * <p>
 * <b>Note:</b> There is currently no encryption for the password field
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/14/18
 */
/**
 * This class is a data structure for admin objects, and reads them into memory from a persistent byte file,
 * called admins.byt, using the loadAdminFile method. When new admins are added, the toAdminFile method can
 * be used to write them to the admin file.
 */

import java.io.*;
import java.util.ArrayList;

public class AdminBank {

    //Data fields
    private final String adminfileName = "admins.byt";
    private File adminFile;
    private ArrayList<Admin> admins;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;


    //Constructor
    @SuppressWarnings("unchecked")
    public AdminBank() {
        admins = new ArrayList();

    }

    public Admin contains (Admin admin) {

        if (admins.contains(admin)) {

            int index = admins.indexOf(admin);
            return admins.get(index);

        }
        else return null;
    }

    /**
     * This method is used for adding admins to the data structure
     * @param admin - admin to add
     * @return successful
     */
    public boolean add(Admin admin) {
        return admins.add(admin);
    }

    /**
     * This method is for removing admins from the data structure
     * @param admin - admin to remove
     * @return successful
     */
    public boolean remove(Admin admin) {

        //Find admin
        if (admins.contains(admin)) {

            //Remove
            admins.remove(admin);
            return true;
        } else {      //Not found
            return false;
        }
    }

    /**
     * This method dumps all the admin objects to the bytefile
     * @throws IOException
     */
    public void toAdminFile() throws IOException {

        //This clears file in case of deletions, etc.
        PrintWriter writer = new PrintWriter(adminFile);
        writer.print("");
        writer.close();

        //Handle object input
        fileOutputStream = new FileOutputStream(adminFile);
        objectOutputStream = new ObjectOutputStream(fileOutputStream);

        //Write admin objects to file
        for (Admin a : admins) {

            objectOutputStream.writeObject(a);
        }
    }


    /**
     * This method reads all admin objects from the bytefile, and will throw
     * a EOF exception when the file has been read in
     * @throws IOException file not found
     * @throws ClassNotFoundException in case class path is broken
     */
    public void loadAdminFile() throws IOException, ClassNotFoundException {

        //Init file
        this.adminFile = new File(adminfileName);

        //Check if file exists, if not, create it
        if (adminFile.createNewFile()) {
            System.out.println("Admin file created in project root");
        } else {
            System.out.println("Admin file exists!");
        }

        //Set up fileinputstream + objectinputstream
        fileInputStream = new FileInputStream(adminFile);
        objectInputStream = new ObjectInputStream(fileInputStream);

        //Read from file and add to admins
        while (true) {
            Admin admin = (Admin) objectInputStream.readObject();
            admins.add(admin);
        }

    }

    public ArrayList toArrayList() {
        return admins;
    }

}//fin
