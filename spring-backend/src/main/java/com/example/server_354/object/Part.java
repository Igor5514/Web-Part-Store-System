package com.example.server_354.object;

import java.util.List;

public class Part {

    private String partName;
    private String groupImage;

    public Part(String partName, String groupImage) {
        this.partName = partName;
        this.groupImage = groupImage;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage;
    }
}

