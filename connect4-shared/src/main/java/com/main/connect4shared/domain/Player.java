package com.main.connect4shared.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
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
 *         &lt;element name="registeredAt" type="{http://www.w3.org/2001/XMLSchema}date"/>
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

    private Date registeredAt;

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

    public Player(Long id, String username, int wins, Date registeredAt) {
        this.id = id;
        this.username = username;
        this.wins = wins;
        this.registeredAt = registeredAt;
    }

    public Player(Long id, String username, String email, String password, int wins, Date registeredAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.wins = wins;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return wins == player.wins && Objects.equals(id, player.id)
                && Objects.equals(username, player.username) && Objects.equals(email, player.email)
                && Objects.equals(password, player.password) && Objects.equals(registeredAt, player.registeredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, wins, registeredAt);
    }

    @Override
    public String getTableName() {
        return "player";
    }

    @Override
    public String getAtrValues() {
        return "'" + this.username +
                "', '" + this.email +
                "', '" + this.password +
                "', '" + this.wins +
                "', CURRENT_DATE()";
    }

    @Override
    public String getAtrNames() {
        return "username,email,password,wins,registeredAt";
    }

    @Override
    public String setAtrValues() {
        return null;
    }

    @Override
    public String getWhereCondition() {
        return "username='" + username + "'";
    }

    @Override
    public String getUpdateQuery() {
        return "wins=" + this.wins;
    }

    @Override
    public String getIdentificator() {
        return String.valueOf(this.id);
    }

    @Override
    public List<GenericEntity> getList(ResultSet resultSet) throws Exception {
        List<GenericEntity> list = new LinkedList<>();

        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            int wins = resultSet.getInt("wins");
            Date registeredAt = resultSet.getDate("registeredAt");

            Player p = new Player(id, username, email, password, wins, registeredAt);

            list.add(p);
        }

        return list;
    }

    @Override
    public String getOrderCondition() {
        return "wins";
    }

    @Override
    public GenericEntity getNewRecord(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("id");
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            int wins = resultSet.getInt("wins");
            Date registeredAt = resultSet.getDate("registeredAt");

            return new Player(id, username, email, password, wins, registeredAt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}