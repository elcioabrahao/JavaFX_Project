/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stmodel.Converters;

public class DevoteeDetails {

    private int devoteeId;
    private String firstName;

    private String lastName;
//    @Column(name = "PHONE")
    private String phone;
//    @Column(name = "EMAIL")
    private String email;
//    @Column(name = "ADDRESS")
    private String address;
//    @Column(name = "CITY")
    private String city;
//    @Column(name = "STATE_DET")
    private String stateDet;
//    @Column(name = "COUNTRY_DET")
    private String countryDet;
//    @Column(name = "ZIP")
    private String zip;
//    @Basic(optional = false)
//    @Column(name = "DATE_CREATED")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
//    @Basic(optional = false)
//    @Column(name = "DATE_MODIFIED")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    private boolean successSave;
    private String addrComplete;
    private String fullName;

    ObservableList<DevoteeDetails> ddList = FXCollections.observableArrayList();

    public boolean isSuccessSave() {
        return successSave;
    }

    public void setAddrComplete(String address, String city, String state, String country, String zip) {
        String addrcomplete = address;
        if (!city.equals("")) {
            if (!addrcomplete.equals("")) {
                addrcomplete = addrcomplete + ", " + city;
            } else {
                addrcomplete = addrcomplete + city;
            }
        }
        if (!state.equals("")) {
            if (!addrcomplete.equals("")) {
                addrcomplete = addrcomplete + ", " + state;
            } else {
                addrcomplete = addrcomplete + state;
            }
        }

        if (!country.equals("")) {
            if (!addrcomplete.equals("")) {
                addrcomplete = addrcomplete + ", " + country;
            } else {
                addrcomplete = addrcomplete + country;
            }
        }

        if (!zip.equals("")) {
            if (!addrcomplete.equals("")) {
                addrcomplete = addrcomplete + ", " + zip;
            } else {
                addrcomplete = addrcomplete + zip;
            }
        }

        this.addrComplete = Converters.replaceNull(addrcomplete);
    }

    public void setFullName(String firstName, String lastName) {
        String fullName = firstName;
        if (!lastName.equals("")) {
            if (!fullName.equals("")) {
                fullName = fullName + " " + lastName;
            } else {
                fullName = fullName + lastName;
            }
        }
        this.fullName = fullName;
    }

    public String getAddrComplete() {
        return addrComplete;
    }

    public String getFullName() {
        return fullName;
    }

    public void setDevoteeDetails(DevoteeDetails dd) {
        this.ddList.add(dd);
    }

    public ObservableList<DevoteeDetails> getDevoteeDetails() {
        return ddList;
    }

    public DevoteeDetails() {
    }

    public DevoteeDetails(int devoteeId) {
        this.devoteeId = devoteeId;
    }

    public DevoteeDetails(int devoteeId, String firstName, Date dateCreated, Date dateModified) {
        this.devoteeId = devoteeId;
        this.firstName = firstName;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public int getDevoteeId() {
        return devoteeId;
    }

    public void setDevoteeId(int devoteeId) {
        this.devoteeId = devoteeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateDet() {
        return stateDet;
    }

    public void setStateDet(String stateDet) {
        this.stateDet = stateDet;
    }

    public String getCountryDet() {
        return countryDet;
    }

    public void setCountryDet(String countryDet) {
        this.countryDet = countryDet;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    /*

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevoteeDetails)) {
            return false;
        }
        DevoteeDetails other = (DevoteeDetails) object;
        if ((this.devoteeId == null && other.devoteeId != null) || (this.devoteeId != null && !this.devoteeId.equals(other.devoteeId))) {
            return false;
        }
        return true;
    }
     */

    public void setSuccessSave(boolean b) {
        this.successSave = b;
    }
}
