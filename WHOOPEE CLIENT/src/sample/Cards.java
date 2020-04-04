package sample;

import javafx.scene.image.Image;
import java.io.File;
import java.net.MalformedURLException;

public class Cards {

    private String color;
    private Image image;
    private int value;



    public Cards() throws MalformedURLException
    {
        this.color = "black";
        this.image = new Image(new File("src\\sample\\black_-1_large.png").toURI().toURL().toString());
        this.value = -1;

    }
    public Cards(String color,Image image,int value) throws MalformedURLException {

        this.color = color;
        this.image = image;
        this.value = value;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
