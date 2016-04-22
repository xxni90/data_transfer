package core;

public class TargetBO {

    String shipper;
    String ship_date;
    String tracking;
    String city;
    String state;
    String zip;
    String weight;
    String customer_weight;

    String zone;
    String freight_rate;
    String residential_surcharge;
    String delivery_area_surcharge;
    String delivery_area_surcharge_extend;
    String fuel_surcharge;
    String Delivery_Confirmation_Signature;
    String Declared_Value;
    String Ground_Commercial;
    String Ground_Residential;
    String I2nd_Day_Air_Residential;

    String Ground_Residential_Third_Party;
    String Ground_Commercial_Third_Party;
    String Delivery_Area_Surcharge_Extended;
    String Returns_Print_and_Mail_Label;
    String Residential_Adjustment;

    String Delivery_Confirmation_Response;
    String Remote_Area_Surcharge;

    String tatal;

    String failReason;

    String Additional_Handling;

    public String getTatal() {
        return tatal;
    }

    public String getAdditional_Handling() {
        return Additional_Handling;
    }

    public void setAdditional_Handling(String additional_Handling) {
        Additional_Handling = additional_Handling;
    }

    public void setTatal(String tatal) {
        this.tatal = tatal;
    }

    public String getFailReason() {
        return failReason;
    }

    public String getGround_Residential() {
        return Ground_Residential;
    }

    public void setGround_Residential(String ground_Residential) {
        Ground_Residential = ground_Residential;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getGround_Residential_Third_Party() {
        return Ground_Residential_Third_Party;
    }

    public String getDelivery_Confirmation_Response() {
        return Delivery_Confirmation_Response;
    }

    public String getCustomer_weight() {
        return customer_weight;
    }

    public void setCustomer_weight(String customer_weight) {
        this.customer_weight = customer_weight;
    }

    public void setDelivery_Confirmation_Response(String delivery_Confirmation_Response) {
        Delivery_Confirmation_Response = delivery_Confirmation_Response;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getRemote_Area_Surcharge() {
        return Remote_Area_Surcharge;
    }

    public void setRemote_Area_Surcharge(String remote_Area_Surcharge) {
        Remote_Area_Surcharge = remote_Area_Surcharge;
    }

    public void setGround_Residential_Third_Party(String ground_Residential_Third_Party) {
        Ground_Residential_Third_Party = ground_Residential_Third_Party;
    }

    public String getGround_Commercial_Third_Party() {
        return Ground_Commercial_Third_Party;
    }

    public void setGround_Commercial_Third_Party(String ground_Commercial_Third_Party) {
        Ground_Commercial_Third_Party = ground_Commercial_Third_Party;
    }

    public String getDelivery_Area_Surcharge_Extended() {
        return Delivery_Area_Surcharge_Extended;
    }

    public void setDelivery_Area_Surcharge_Extended(String delivery_Area_Surcharge_Extended) {
        Delivery_Area_Surcharge_Extended = delivery_Area_Surcharge_Extended;
    }

    public String getReturns_Print_and_Mail_Label() {
        return Returns_Print_and_Mail_Label;
    }

    public void setReturns_Print_and_Mail_Label(String returns_Print_and_Mail_Label) {
        Returns_Print_and_Mail_Label = returns_Print_and_Mail_Label;
    }

    public String getResidential_Adjustment() {
        return Residential_Adjustment;
    }

    public void setResidential_Adjustment(String residential_Adjustment) {
        Residential_Adjustment = residential_Adjustment;
    }

    public String getShip_date() {
        return ship_date;
    }

    public void setShip_date(String ship_date) {
        this.ship_date = ship_date;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getFreight_rate() {
        return freight_rate;
    }

    public void setFreight_rate(String freight_rate) {
        this.freight_rate = freight_rate;
    }

    public String getResidential_surcharge() {
        return residential_surcharge;
    }

    public void setResidential_surcharge(String residential_surcharge) {
        this.residential_surcharge = residential_surcharge;
    }

    public String getDelivery_area_surcharge() {
        return delivery_area_surcharge;
    }

    public void setDelivery_area_surcharge(String delivery_area_surcharge) {
        this.delivery_area_surcharge = delivery_area_surcharge;
    }

    public String getDelivery_area_surcharge_extend() {
        return delivery_area_surcharge_extend;
    }

    public void setDelivery_area_surcharge_extend(String delivery_area_surcharge_extend) {
        this.delivery_area_surcharge_extend = delivery_area_surcharge_extend;
    }

    public String getFuel_surcharge() {
        return fuel_surcharge;
    }

    public void setFuel_surcharge(String fuel_surcharge) {
        this.fuel_surcharge = fuel_surcharge;
    }

    public String getDelivery_Confirmation_Signature() {
        return Delivery_Confirmation_Signature;
    }

    public void setDelivery_Confirmation_Signature(String delivery_Confirmation_Signature) {
        Delivery_Confirmation_Signature = delivery_Confirmation_Signature;
    }

    public String getDeclared_Value() {
        return Declared_Value;
    }

    public void setDeclared_Value(String declared_Value) {
        Declared_Value = declared_Value;
    }

    public String getGround_Commercial() {
        return Ground_Commercial;
    }

    public void setGround_Commercial(String ground_Commercial) {
        Ground_Commercial = ground_Commercial;
    }

    public String getI2nd_Day_Air_Residential() {
        return I2nd_Day_Air_Residential;
    }

    public void setI2nd_Day_Air_Residential(String i2nd_Day_Air_Residential) {
        I2nd_Day_Air_Residential = i2nd_Day_Air_Residential;
    }

}

//ship_date("ship date", 0),
//tracking("tracking", 1),
//city("city", 2),
//state("state", 3),
//zip("zip", 4),
//weight("weight", 5),
//zone("zone", 6),
//freight_rate("freight rate", 7),
//residential_surcharge("residential surcharge", 8),
//delivery_area_surcharge("delivery area surcharge", 9),
//delivery_area_surcharge_extend("delivery area surcharge extend", 10),
//fuel_surcharge("fuel surcharge", 11),
//Delivery_Confirmation_Signature("Delivery Confirmation Signature", 12),
//Declared_Value("Declared Value", 13),
//Ground_Commercial("Ground Commercial", 14);