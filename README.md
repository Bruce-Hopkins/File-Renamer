# File-Renamer


## Description
This is made to add zeroes to the end of folders and files to allow the files to be sorted in the right order on certain devices.

## Usage 

```java

// Create new instance and use the find() method to specify the main folder which will be used
DirectoryRenamer directoryFinder = new DirectoryRenamer();
File[] directories = directoryFinder.find(directory);

// Go through all the folders in that directory
for(File directory : directories) {
  if (directory.isDirectory()) {
  
      /* The first parameter is the current directory it will rename. 
      The Second is the common name among the files. (For instance if the folders are named "page1.png" and "page2.png" the common name would be "page")
      */
      String newDirectoryName = directoryFinder.rename(directory, "page");
      
      // imageRenamer has the same mehthod as directoryFinder. Only difference is imageRenamer will only accept ".png", ".jpg", or ".jpeg" files.    
}
