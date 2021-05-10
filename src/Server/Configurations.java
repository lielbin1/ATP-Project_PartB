package Server;
import java.io.*;
import java.util.Properties;

public  class Configurations {
    private static Configurations instance = null;
    private static  String filename = "resources/config.properties";

    //singletone class
    private Configurations() {

    }

    public static Configurations getInstance()
    {
        if (instance == null)
            instance = new Configurations();

        return instance;
    }

    // set defalut properties
    public  void SetConfiguration() {
        OutputStream output;
        Properties prop = new Properties();


        try {
            output = new FileOutputStream(filename);



            //add the properties value
            int number = Runtime.getRuntime().availableProcessors() * 2;
            prop.setProperty("threadPoolSize", String.valueOf(number));
            prop.setProperty("mazeSearchingAlgorithm", "BreadthFirstSearch");
            prop.setProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");

            // save properties to project root folder
            prop.store(output, null);

            output.close();
        } catch (FileNotFoundException file) {
            System.out.println("configurations file not found");
            //     SetConfiguration();
        }catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    // get properties
    public Properties getProperties(){
        Properties prop= new Properties();
        InputStream input = null;
        //      String filename = "config.properties";


        try {
            input = new FileInputStream(filename);
            try {
                prop.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // if empty make defalut
        if(prop.isEmpty())
        {
            getInstance().SetConfiguration();
            return getProperties();
        }

        return prop;
    }
}
