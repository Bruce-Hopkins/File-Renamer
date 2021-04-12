import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        String dir = "";

        if (args.length > 0) {
            dir = args[0];
            System.out.println(dir);

            //If the user types -url then receive the url
            if (args[0].contains("path")) dir = args[0].split("-path ")[1];
            else throw new IOException();
        }
        
        // In case you forget to add the "/"
        if (dir.endsWith("/")) System.out.println("Directory is good");
        else dir = dir + "/";

        DirectoryRenamer directoryFinder = new DirectoryRenamer();

        //Must be called before rename files
        File[] directories = directoryFinder.find(dir);

        for(File directory : directories) {

            if (directory.isDirectory()) {
                // Rename the directory with whatever naming system you use
                // "namingSystem" is whatever word your directories start with
                String newDirectoryName = directoryFinder.rename(directory, "");

                ImageRenamer imageRenamer = new ImageRenamer();
                File[] files = imageRenamer.find(newDirectoryName);
                for (File file : files) {
                    // Same process for files
                    if (file.toString().contains(".png") || file.toString().contains(".jpg")
                            || file.toString().contains(".jpeg")) {
                        imageRenamer.rename(file, "page");
                        System.out.println(file);
                    }
                }
            } else System.out.println("Not a folder");
        }
    }
}

