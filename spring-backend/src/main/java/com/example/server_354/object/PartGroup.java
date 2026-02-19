package com.example.server_354.object;

import java.util.List;

public class PartGroup {

    private String groupName;
    private String groupImage;
    private List<String> partsList;

    public PartGroup(String groupName, String groupImage, List<String> partsList) {
        this.groupName = groupName;
        this.groupImage = groupImage;
        this.partsList = partsList;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage;
    }

    public List<String> getPartsList() {
        return partsList;
    }

    public void setPartsList(List<String> partsList) {
        this.partsList = partsList;
    }
}
