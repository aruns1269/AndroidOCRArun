package com.principal.ind.advisormobiledropticket;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ProposedInsured implements Parcelable {
    private String firstName;
    private String lastName;
    private String gender;
    private String addressLine1;
    private String addressLine2;
    private String zipCode;
    private String phoneNumber;
    private String email;
    private String state;
    private String policyLength;
    private String coverageAmount;
    private List<String> rider;
    private String premium;
    private String billingFrequency;
    private String underwritingClass;
    private String rating;
    private boolean tobacco;
    private boolean acceleratedUnderWritingRequired;
    private boolean orderExamRequirements;
    private String vendorName;


    protected ProposedInsured(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        gender = in.readString();
        addressLine1 = in.readString();
        addressLine2 = in.readString();
        zipCode = in.readString();
        phoneNumber = in.readString();
        email = in.readString();
        state = in.readString();
        policyLength = in.readString();
        coverageAmount = in.readString();
        rider = in.createStringArrayList();
        premium = in.readString();
        billingFrequency = in.readString();
        underwritingClass = in.readString();
        rating = in.readString();
        tobacco = in.readByte() != 0;
    }

    public ProposedInsured() {
        super();
    }

    public static final Creator<ProposedInsured> CREATOR = new Creator<ProposedInsured>() {
        @Override
        public ProposedInsured createFromParcel(Parcel in) {
            return new ProposedInsured(in);
        }

        @Override
        public ProposedInsured[] newArray(int size) {
            return new ProposedInsured[size];
        }
    };

    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setPolicyLength(String policyLength) {
        this.policyLength = policyLength;
    }
    public void setCoverageAmount(String coverageAmount) {
        this.coverageAmount = coverageAmount;
    }
    public void setRider(List<String> rider) {
        this.rider = rider;
    }
    public void setPremium(String premium) {
        this.premium = premium;
    }
    public void setAcceleratedUnderWritingRequired(boolean acceleratedUnderWritingRequired) {
        this.acceleratedUnderWritingRequired = acceleratedUnderWritingRequired;
    }
    public  void setOrderExamRequirements(boolean orderExamRequirements) {
        this.orderExamRequirements = orderExamRequirements;
    }
    public void setBillingFrequency(String billingFrequency) {
        this.billingFrequency = billingFrequency;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setTobacco(boolean tobacco) {
        this.tobacco = tobacco;
    }

    public void setUnderwritingClass(String underwritingClass) {
        this.underwritingClass = underwritingClass;
    }
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getCoverageAmount() {
        return this.coverageAmount;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getState() {
        return state;
    }

    public String getPolicyLength() {
        return policyLength;
    }

    public List<String> getRider() {
        return rider;
    }

    public String getPremium() {
        return premium;
    }

    public String getBillingFrequency() {
        return billingFrequency;
    }

    public String getUnderwritingClass() {
        return underwritingClass;
    }

    public String getRating() {
        return rating;
    }

    public boolean isTobacco() {
        return tobacco;
    }

    public boolean isAcceleratedUnderWritingRequired() {
        return acceleratedUnderWritingRequired;
    }

    public boolean isOrderExamRequirements() {
        return orderExamRequirements;
    }

    public String getVendorName() {
        return vendorName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(gender);
        parcel.writeString(addressLine1);
        parcel.writeString(addressLine2);
        parcel.writeString(zipCode);
        parcel.writeString(phoneNumber);
        parcel.writeString(email);
        parcel.writeString(state);
        parcel.writeString(policyLength);
        parcel.writeString(coverageAmount);
        parcel.writeStringList(rider);
        parcel.writeString(premium);
        parcel.writeString(billingFrequency);
        parcel.writeString(underwritingClass);
        parcel.writeString(rating);
        parcel.writeByte((byte) (tobacco ? 1 : 0));
    }
}
