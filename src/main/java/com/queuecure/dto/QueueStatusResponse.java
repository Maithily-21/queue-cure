package com.queuecure.dto;

public class QueueStatusResponse {

    private Integer currentToken;
    private Integer waitingCount;

    public QueueStatusResponse(
            Integer currentToken,
            Integer waitingCount) {

        this.currentToken = currentToken;
        this.waitingCount = waitingCount;
    }

    public Integer getCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(Integer currentToken) {
        this.currentToken = currentToken;
    }

    public Integer getWaitingCount() {
        return waitingCount;
    }

    public void setWaitingCount(Integer waitingCount) {
        this.waitingCount = waitingCount;
    }
}
