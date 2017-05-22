package com.amur.areas.users.models.binding.users;


import com.amur.areas.users.annotations.IsPasswordConfirmed;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@IsPasswordConfirmed
public class RegistrationModel {

    @NotNull(message = "Моля въведете име!")
    @Pattern(regexp = "[А-Яа-я]+", message = "Въвели сте невалидно име!")
    private String firstName;

    @NotNull(message = "Моля въведете фамилия!")
    @Pattern(regexp = "[А-Яа-я]+", message = "Въвели сте невалидно име!")
    private String lastName;

    @Email(message = "Въвели сте невалиден имейл адрес!")
    private String email;

    @NotNull(message = "Моля въведете телефонен номер!")
    @Pattern(regexp = "[0-9]+")
    private String phoneNumber;

    private String faxNumber;

    private String business;

    @NotNull(message = "Моля въведете адрес!")
    private String address;

    private String addressAddition;

    @NotNull(message = "Моля въведете населено място!")
    @Pattern(regexp = "[А-Яа-я]+", message = "Въвели сте невалидно населено място!")
    private String town;

    //@NotNull(message = "Моля въведете пощенски код!")
    private int postalCode;

    @NotEmpty(message = "Моля изберете държава!")
    private String countryName;

    @NotEmpty(message = "Моля изберете област!")
    private String regionName;

    @NotNull(message = "Моля въведете потребителско име!")
    @Size(min = 4, message = "Потребителското име трябва да бъде поне четири символа!")
    private String username;

    @NotNull(message = "Моля въведете парола!")
    @Size(min = 6, message = "Паролата трябва да бъде поне шест символа!")
    private String password;

    private String confirmPassword;

    public RegistrationModel() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressAddition() {
        return addressAddition;
    }

    public void setAddressAddition(String addressAddition) {
        this.addressAddition = addressAddition;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
