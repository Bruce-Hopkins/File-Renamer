import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class DirectoryRenamer {

    private String currentDirectory;

    public void setDirectory(String dir) {
        this.currentDirectory = dir;
    }
    public String getCurrentDirectory() {
        return this.currentDirectory;
    }

    public File[] find(String dir) {
        setDirectory(dir);
        File directoryPath = new File(dir);
        return directoryPath.listFiles();
    }

    // Check if the String can be converted to a Double
    public boolean isDouble(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    // Check if the String can be converted to a Integer
    public boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    // Renames directory
    public String rename(File dir, String namingSystem) throws IOException {
        String directoryNumber;

        Path path = Paths.get(dir.toString());
        path = path.getFileName();

        String stringOfPath = path.toString();

        // If the filename contains name.
        if (stringOfPath.contains(namingSystem) && !namingSystem.equals("")) directoryNumber = stringOfPath.split(namingSystem)[1];
        else directoryNumber = stringOfPath;

        String newDirectoryNumber;

        // Try to turn into an Int, if not try a double
        if (isInteger(directoryNumber)) {

            int intDirectoryNumber = Integer.parseInt(directoryNumber);
            if (intDirectoryNumber < 100) {

                if (intDirectoryNumber < 10) {
                    newDirectoryNumber = "00" + intDirectoryNumber;
                } else {
                    newDirectoryNumber = "0" + intDirectoryNumber;
                }
            } else {
                newDirectoryNumber = String.valueOf(intDirectoryNumber);
            }
        }

        else if (isDouble(directoryNumber)){

            double doubleDirectoryNumber = Double.parseDouble(directoryNumber);
            if (doubleDirectoryNumber < 100) {

                if (doubleDirectoryNumber < 10) {
                    newDirectoryNumber = "00" + doubleDirectoryNumber ;
                } else {
                    newDirectoryNumber = "0" + doubleDirectoryNumber;
                }
            } else {
                newDirectoryNumber = String.valueOf(doubleDirectoryNumber);
            }
        }

        else throw new IOException("Not a number");

        // Then revert back into a String
        File newName = new File(getCurrentDirectory() + namingSystem
                + newDirectoryNumber);
        if(dir.renameTo(newName)) {
            System.out.println(newName);
            return newName.toString();
        } else {
            throw new IOException();
        }


    }






}
