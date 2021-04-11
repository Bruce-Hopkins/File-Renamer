import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageRenamer extends DirectoryRenamer {

    @Override
    public String rename(File dir, String namingSystem) throws IOException {


        String fileForChange;
        String fileNumber;
        String ext;

        Path path = Paths.get(dir.toString());
        path = path.getFileName();

        String stringOfPath = path.toString();

        // If the filename contains name of the file
        if (stringOfPath.contains(namingSystem)) fileForChange = stringOfPath.split(namingSystem)[1];
        else fileForChange = stringOfPath;

        // Checks what file it is
        if (fileForChange.contains(".png")) {
            fileNumber = fileForChange.split(".png")[0];
            ext = ".png";
        } else if(fileForChange.contains(".jpg")) {
            fileNumber = fileForChange.split(".jpg")[0];
            ext = ".jpg";
        } else if (fileForChange.contains(".jpeg")) {
            fileNumber = fileForChange.split(".jpeg")[0];
            ext = ".jpeg";
        } else throw new IOException();

        // Adds a certain amount of zeroes depending on the size of the int
        int intFileNumber = Integer.parseInt(fileNumber);
        if ( intFileNumber < 100) {
            if (intFileNumber < 10) {
                fileNumber = "00" + intFileNumber;
            } else {
                fileNumber = "0" + intFileNumber;
            }
        } else {
            fileNumber = "" + intFileNumber;
        }

        // Finally, Rename the file with the necessary parameters
        File newName = new File(getCurrentDirectory() + "/" + namingSystem + fileNumber + ext);
        System.out.println(newName);
        boolean nameChange = dir.renameTo(newName);
        if(nameChange) {
            return newName.toString();
        } else {
            throw new IOException();
        }
    }
}
