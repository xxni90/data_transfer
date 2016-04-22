package core;

public enum TittleEm {
    ship_date("ship date", 0),
    tracking("tracking", 1),
    city("city", 2),
    state("state", 3),
    zip("zip", 4),
    weight("weight", 5),
    customer_weight("custom weight", 5),
    zone("zone", 6),
    freight_rate("freight rate", 7),
    residential_surcharge("residential surcharge", 8),
    delivery_area_surcharge("delivery area surcharge", 9),
    delivery_area_surcharge_extend("delivery area surcharge extend", 10),
    fuel_surcharge("fuel surcharge", 11),
    Delivery_Confirmation_Signature("Delivery Confirmation Signature", 12),
    Declared_Value("Declared Value", 13),
    Ground_Commercial("Ground Commercial", 14),
    Ground_Residential("Ground Residential", 14),

    shipper("shipper", 15),
    I2nd_Day_Air_Residential("2nd Day Air Residential", 16),
    Ground_Residential_Third_Party("Ground Residential Third Party", 17),
    Ground_Commercial_Third_Party("Ground Commercial Third Party", 17),
    Delivery_Area_Surcharge_Extended("Delivery Area Surcharge - Extended", 1),
    Returns_Print_and_Mail_Label("Returns Print and Mail Label", 1),
    Residential_Adjustment("Residential Adjustment", 15),
    Delivery_Confirmation_Response("Delivery Confirmation Response", 15),
    Remote_Area_Surcharge("Remote Area Surcharge", 15),
    Additional_Handling("Additional Handling", 15);

    String title;
    int    index;

    private TittleEm(String title, int index) {
        this.title = title;
        this.index = index;
    }

}
