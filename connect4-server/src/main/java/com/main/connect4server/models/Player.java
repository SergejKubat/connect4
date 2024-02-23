package com.main.connect4server.models;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
public class Player implements GenericEntity {
    private Long id;

    private String username;

    private String email;

    private String password;

    private int wins;

    private int defeats;

    private Date registeredAt;

    private Date signInDate;

    public Player() {
    }

    public Player(String username) {
        this.username = username;
    }

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Player(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return wins == player.wins && defeats == player.defeats && Objects.equals(id, player.id)
                && Objects.equals(username, player.username) && Objects.equals(email, player.email)
                && Objects.equals(password, player.password) && Objects.equals(registeredAt, player.registeredAt)
                && Objects.equals(signInDate, player.signInDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, wins, defeats, registeredAt, signInDate);
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public String getAtrValues() {
        return null;
    }

    @Override
    public String getAtrNames() {
        return null;
    }

    @Override
    public String setAtrValues() {
        return null;
    }

    @Override
    public String getWhereCondition() {
        return null;
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public String getIdentificator() {
        return null;
    }

    @Override
    public List<GenericEntity> getList(ResultSet resultSet) throws Exception {
        return null;
    }

    @Override
    public String getOrderCondition() {
        return null;
    }

    @Override
    public GenericEntity getNewRecord(ResultSet rs) {
        return null;
    }
}
