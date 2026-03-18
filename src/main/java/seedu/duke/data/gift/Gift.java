package seedu.duke.data.gift;

import java.util.logging.Logger;


public class Gift {
    private static final Logger logger = Logger.getLogger(Gift.class.getName());
    private final  String giftName;
    private  boolean isDelivered;


    public Gift(String giftName){
        assert giftName != null : "Gift name should not be null";
        if (giftName == null || giftName.isBlank()) {
            logger.warning("Attempted to create Gift with invalid name");
            throw new IllegalArgumentException("Gift name cannot be null or empty");
        }

        this.giftName= giftName;
        this.isDelivered= false;
        logger.info("Created gift: " + giftName);
    }

    public String getGiftName(){
        return giftName;
    }
    public boolean isDelivered(){
        return isDelivered;
    }
    public void setDelivered(boolean delivered){
        this.isDelivered=delivered;
        logger.info("Gift '" + giftName + "' delivery status set to: " + delivered);
    }
    @Override
    public String toString(){
        return (isDelivered ? "[Delivered] " : "[Undelivered]") + giftName;
    }
}

