package utilities;

import javax.json.JsonObject;

/**
 * Created by WataruS on 10/22/2016.
 */
public class JsonHandlers{
    private String filePath;


    public JsonHandlers(String filePath) {
        this.setFilePath(filePath);

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public void add(Object object){
        
    }
    
    public JsonObject get(Object object){
     return null;
    }

    public JsonObject getAll(){
        return null;
    }



    public void remove(Object object){
        
    }
    
    public void modify(Object object){
        
    }
}
