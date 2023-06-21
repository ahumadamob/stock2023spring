package imb3.stock.controller;

public class APIResponse<T> {
    private int status;
    private String messages;
    private T data;
    
    public APIResponse(int status, String string, T data) {
        this.status = status;
        this.messages = string;
        this.data = data;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getMessages() {
        return messages;
    }
    
    public void setMessages(String messages) {
        this.messages = messages;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
}
