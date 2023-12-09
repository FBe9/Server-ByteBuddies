package entitis;

import java.io.Serializable;
import java.util.Objects;
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte1.other;

/**
 * Entity to establish the id for the N:M with atributes relation.
 * @author irati
 */
public class EnrolledId implements Serializable {
    private Long accountId;
    private Long customerId;

    /**
     * Default constructor for creating an empty enrollment ID.
     */
    public EnrolledId() {
    }

    /**
     * Parameterized constructor for creating an enrollment ID with specified account and customer IDs.
     *
     * @param accountId   the account ID
     * @param customerId  the customer ID
     */
    public EnrolledId(Long accountId, Long customerId) {
        this.accountId = accountId;
        this.customerId = customerId;
    }

    /**
     * Gets the account ID of the enrollment ID.
     *
     * @return the account ID
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * Sets the account ID of the enrollment ID.
     *
     * @param accountId the account ID to be set
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets the customer ID of the enrollment ID.
     *
     * @return the customer ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID of the enrollment ID.
     *
     * @param customerId the customer ID to be set
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * Computes the hash code for this enrollment ID.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.accountId);
        hash = 31 * hash + Objects.hashCode(this.customerId);
        return hash;
    }

    /**
     * Checks if this enrollment ID is equal to another object.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EnrolledId other = (EnrolledId) obj;
        return Objects.equals(this.accountId, other.accountId) && Objects.equals(this.customerId, other.customerId);
    }

    /**
     * Generates a string representation of this enrollment ID.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "EnrolledId{" + "accountId=" + accountId + ", customerId=" + customerId + '}';
    }

}
