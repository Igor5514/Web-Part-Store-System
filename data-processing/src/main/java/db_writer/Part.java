package db_writer;

import jdk.jfr.Enabled;


public class Part {

    private String partType;
    private byte[] image;

    public Part(String partType, byte[] image) {
        this.partType = partType;
        this.image = image;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
