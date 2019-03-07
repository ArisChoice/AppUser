package com.app.barber.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by harish on 8/2/19.
 */

public class DistricResponseModel {

    /**
     * Message : List
     * Status : 201
     * List : [{"Disabled":false,"Group":null,"Selected":false,"Text":"EC1 Head district (EC)","Value":"1"},{"Disabled":false,"Group":null,"Selected":false,"Text":"EC2 Bishopsgate (EC)","Value":"2"},{"Disabled":false,"Group":null,"Selected":false,"Text":"EC3 Fenchurch Street (EC)","Value":"3"},{"Disabled":false,"Group":null,"Selected":false,"Text":"EC4 Fleet Street (EC)","Value":"4"},{"Disabled":false,"Group":null,"Selected":false,"Text":"WC1 Head district (WC)","Value":"5"},{"Disabled":false,"Group":null,"Selected":false,"Text":"NW1 Head district (NW)","Value":"7"},{"Disabled":false,"Group":null,"Selected":false,"Text":"NW2 Cricklewood (NW)","Value":"8"},{"Disabled":false,"Group":null,"Selected":false,"Text":"NW3 Hampstead (NW)","Value":"9"},{"Disabled":false,"Group":null,"Selected":false,"Text":"NW4 Hendon (NW)","Value":"10"},{"Disabled":false,"Group":null,"Selected":false,"Text":"NW5 Kentish Town (NW)","Value":"11"},{"Disabled":false,"Group":null,"Selected":false,"Text":"NW6 Kilburn (NW)","Value":"12"},{"Disabled":false,"Group":null,"Selected":false,"Text":"NW6 Kilburn (NW)","Value":"13"},{"Disabled":false,"Group":null,"Selected":false,"Text":"NW7 Mill Hill (NW)","Value":"14"},{"Disabled":false,"Group":null,"Selected":false,"Text":"NW8 St John's Wood (NW)","Value":"15"},{"Disabled":false,"Group":null,"Selected":false,"Text":"NW9 The Hyde (NW)","Value":"16"},{"Disabled":false,"Group":null,"Selected":false,"Text":"NW10 Willesden (NW)","Value":"17"},{"Disabled":false,"Group":null,"Selected":false,"Text":"NW11 Golders Green (NW)","Value":"18"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E1 Head district (E)","Value":"19"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E2 Bethnal Green (E)","Value":"20"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E3 Bow (E)","Value":"21"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E4 Chingford (E)","Value":"22"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E5 Clapton (E)","Value":"23"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E6 East Ham (E)","Value":"24"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E7 Forest Gate (E)","Value":"25"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E8 Hackney (E)","Value":"26"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E9 Homerton (E)","Value":"27"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E10 Leyton (E)","Value":"28"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E11 Leytonstone (E)","Value":"29"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E12 Manor Park (E)","Value":"30"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E13 Plaistow (E)","Value":"31"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E14 Poplar (E)","Value":"32"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E15 Stratford (E)","Value":"33"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E16 Victoria Docks and North  (E)","Value":"34"},{"Disabled":false,"Group":null,"Selected":false,"Text":"Woolwich (E)","Value":"35"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E17 Walthamstow (E)","Value":"36"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E18 Woodford and South Woodford (E)","Value":"37"},{"Disabled":false,"Group":null,"Selected":false,"Text":"E20 Olympic Park (E)","Value":"38"},{"Disabled":false,"Group":null,"Selected":false,"Text":"N1 Head district (N)","Value":"39"},{"Disabled":false,"Group":null,"Selected":false,"Text":"N2 East Finchley (N)","Value":"40"},{"Disabled":false,"Group":null,"Selected":false,"Text":"N3 Finchley (N)","Value":"41"},{"Disabled":false,"Group":null,"Selected":false,"Text":"N4 Finsbury Park (N)","Value":"42"},{"Disabled":false,"Group":null,"Selected":false,"Text":"N5 Highbury (N)","Value":"43"},{"Disabled":false,"Group":null,"Selected":false,"Text":"N6 Highgate (N)","Value":"44"},{"Disabled":false,"Group":null,"Selected":false,"Text":"N9 Lower Edmont 02-08 11:33:24.086 28044-28290/com.app.trimcheck.customer D/OkHttp: on (N)","Value":"47"},{"Disabled":false,"Group":null,"Selected":false,"Text":"N10 Muswell Hill (N)","Value":"48"},{"Disabled":false,"Group":null,"Selected":false,"Text":"N11 New Southgate (N)","Value":"49"},{"Disabled":false,"Group":null,"Selected":false,"Text":"N13 Palmers Green (N)","Value":"51"},{"Disabled":false,"Group":null,"Selected":false,"Text":"SE2 Abbey Wood (SE)","Value":"75"},{"Disabled":false,"Group":null,"Selected":false,"Text":"SE3 Blackheath (SE)","Value":"76"},{"Disabled":false,"Group":null,"Selected":false,"Text":"SE4 Brockley (SE)","Value":"77"},{"Disabled":false,"Group":null,"Selected":false,"Text":"SE5 Camberwell (SE)","Value":"78"},{"Disabled":false,"Group":null,"Selected":false,"Text":"SW9 Stockwell (SW)","Value":"110"}]
     */

    @SerializedName("Message")
    private String Message;
    @SerializedName("Status")
    private int Status;
    @SerializedName("List")
    private java.util.List<ListBean> List;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public List<ListBean> getList() {
        return List;
    }

    public void setList(List<ListBean> List) {
        this.List = List;
    }

    public static class ListBean {
        /**
         * Disabled : false
         * Group : null
         * Selected : false
         * Text : EC1 Head district (EC)
         * Value : 1
         */

        @SerializedName("Disabled")
        private boolean Disabled;
        @SerializedName("Group")
        private Object Group;
        @SerializedName("Selected")
        private boolean Selected;
        @SerializedName("Text")
        private String Text;
        @SerializedName("Value")
        private String Value;

        public boolean isDisabled() {
            return Disabled;
        }

        public void setDisabled(boolean Disabled) {
            this.Disabled = Disabled;
        }

        public Object getGroup() {
            return Group;
        }

        public void setGroup(Object Group) {
            this.Group = Group;
        }

        public boolean isSelected() {
            return Selected;
        }

        public void setSelected(boolean Selected) {
            this.Selected = Selected;
        }

        public String getText() {
            return Text;
        }

        public void setText(String Text) {
            this.Text = Text;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }
    }
}
