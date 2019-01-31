package ConstantsCreator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonWebElement {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("XPath")
    @Expose
    private String xPath;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("Location")
    @Expose
    private List<Location> location = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXPath() {
        return xPath;
    }

    public void setXPath(String xPath) {
        this.xPath = xPath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }



    public class Location {

        @SerializedName("X")
        @Expose
        private Integer x;
        @SerializedName("Y")
        @Expose
        private Integer y;

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }

    }

}
