package com.main.connect4client.models;

import java.util.Date;

/**
 * <p>Java class for player complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="player">
 *   &lt;complexContent>
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wins" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="defeats" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="registeredAt" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="signInDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class Player {
    private Long id;

    private String username;

    private String email;

    private String password;

    private int wins;

    private int defeats;

    private Date registeredAt;

    private Date signInDate;

    public Player(Long id, String username, int wins, int defeats, Date registeredAt) {
        this.id = id;
        this.username = username;
        this.wins = wins;
        this.defeats = defeats;
        this.registeredAt = registeredAt;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link Long }
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param id allowed object is
     *           {@link Long }
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the value of the username property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     *
     * @param username allowed object is
     *                 {@link String }
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the value of the email property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     *
     * @param email allowed object is
     *              {@link String }
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the value of the password property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     *
     * @param password allowed object is
     *                 {@link String }
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the value of the wins property.
     *
     * @return possible object is
     * {@link int }
     */
    public int getWins() {
        return wins;
    }

    /**
     * Sets the value of the wins property.
     *
     * @param wins allowed object is
     *             {@link int }
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * Gets the value of the defeats' property.
     *
     * @return possible object is
     * {@link int }
     */
    public int getDefeats() {
        return defeats;
    }

    /**
     * Sets the value of the defeats' property.
     *
     * @param defeats allowed object is
     *                {@link int }
     */
    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    /**
     * Gets the value of the registeredAt property.
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getRegisteredAt() {
        return registeredAt;
    }

    /**
     * Sets the value of the registeredAt property.
     *
     * @param registeredAt allowed object is
     *                     {@link Date }
     */
    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    /**
     * Gets the value of the signInDate property.
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getSignInDate() {
        return signInDate;
    }

    /**
     * Sets the value of the signInDate property.
     *
     * @param signInDate allowed object is
     *                   {@link Date }
     */
    public void setSignInDate(Date signInDate) {
        this.signInDate = signInDate;
    }
}
