package constants;

import java.io.File;

public abstract class FileConstants {
    private void FileConstants(){
    }
//ConfigFilePath
    public static final String CONFIG_FILE_PATH=System.getProperty("user.dir")+ File.separator+"src"
            +File.separator+"main"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties";

    //vendor page
    public static final String SCHEMA_FILE_PATH=System.getProperty("user.dir")+ File.separator+"src"
            +File.separator+"test"+File.separator+"resources"+File.separator+"schema.vendor";

    //Vendor Schema
    public static final String CREATE_VENDOR_SCHEMA=SCHEMA_FILE_PATH + File.separator+"createVendorSchema.json";

    //Brand Schema
    public static final String CREATE_BRAND_SCHEMA=SCHEMA_FILE_PATH + File.separator+"createBrandsSchema.json";


}
