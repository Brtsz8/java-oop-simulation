import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;

public class Settings {
    private int tileSize;
    private int width;
    private int height;

    //constructor
    public Settings(String input){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));
            setTileSize(Integer.parseInt(reader.readLine()));
            setWidth(Integer.parseInt(reader.readLine()) * getTileSize());
            setHeight(Integer.parseInt(reader.readLine()) * getTileSize());
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Getters
    // Getter for tile size
    public int getTileSize() {
        return tileSize;
    }
    // Getter for width
    public int getWidth() {
        return width;
    }
    // Getter for height
    public int getHeight() {
        return height;
    }

    //Setters
    // Setter for tile size
    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }
    // Setter for width
    public void setWidth(int width) {
        this.width = width;
    }
    //  Setter for height
    public void setHeight(int height) {
        this.height = height;
    }
}
