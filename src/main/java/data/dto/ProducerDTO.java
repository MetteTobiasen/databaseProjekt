package data.dto;

public class ProducerDTO {
    private String producerName;
    private String email;
    private int areaCode;
    private int phoneNumber;

    public ProducerDTO(String producerName, String email, int areaCode, int phoneNumber){
        this.producerName = producerName;
        this.email = email;
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
    }

    public ProducerDTO(String producerName){
        this.producerName = producerName;
    }

    public String getProducerName(){
        return producerName;
    }

    public void setProducerName(String producerName){
        this.producerName = producerName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
