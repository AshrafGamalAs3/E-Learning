package com.example.e_learning;

public class MessageData {
   String userId,massage,massageId,userName;

    public MessageData(String userId, String massage, String userName) {
        this.userId = userId;
        this.massage = massage;

        this.userName = userName;
    }

    public MessageData() {
    }

    public MessageData(String userId, String massage) {
        this.userId = userId;
        this.massage = massage;
    }

    public String getUserId() {
        return userId;
    }

    public String getMassage() {
        return massage;
    }

    public String getMassageId() {
        return massageId;
    }

    public String getUserName() {
        return userName;
    }

    public void setMassageId(String massageId) {
        this.massageId = massageId;
    }
}
