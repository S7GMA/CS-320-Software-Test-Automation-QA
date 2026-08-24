import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory store for Contact objects, keyed by contactId.
 * No persistence, no UI — this milestone is service-layer only.
 */
public class ContactService {

    private final Map<String, Contact> contacts = new ConcurrentHashMap<>();

    /**
     * Adds a new contact. Fails if a contact with the same ID already exists,
     * since contactId must be unique.
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("Contact ID already exists: " + contact.getContactId());
        }
        contacts.put(contact.getContactId(), contact);
    }

    public void deleteContact(String contactId) {
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID not found: " + contactId);
        }
        contacts.remove(contactId);
    }

    public void updateFirstName(String contactId, String firstName) {
        getExistingContact(contactId).setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        getExistingContact(contactId).setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        getExistingContact(contactId).setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        getExistingContact(contactId).setAddress(address);
    }

    /**
     * Exposed for tests/callers that need to verify state without
     * having to go through individual update methods.
     */
    public Contact getContact(String contactId) {
        return getExistingContact(contactId);
    }

    private Contact getExistingContact(String contactId) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found: " + contactId);
        }
        return contact;
    }
}
