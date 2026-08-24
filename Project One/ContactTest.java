import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    private Contact validContact() {
        return new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
    }

    @Test
    void constructsWithValidFields() {
        Contact c = validContact();
        assertEquals("1234567890", c.getContactId());
        assertEquals("John", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main St", c.getAddress());
    }

    // --- contactId ---

    @Test
    void nullContactIdThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Contact(null, "John", "Doe", "1234567890", "123 Main St"));
    }

    @Test
    void contactIdOver10CharsThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St"));
    }

    @Test
    void contactIdExactly10CharsIsAllowed() {
        assertDoesNotThrow(() -> new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St"));
    }

    // --- firstName ---

    @Test
    void nullFirstNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", null, "Doe", "1234567890", "123 Main St"));
    }

    @Test
    void firstNameOver10CharsThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "12345678901", "Doe", "1234567890", "123 Main St"));
    }

    // --- lastName ---

    @Test
    void nullLastNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "John", null, "1234567890", "123 Main St"));
    }

    @Test
    void lastNameOver10CharsThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "John", "12345678901", "1234567890", "123 Main St"));
    }

    // --- phone ---

    @Test
    void nullPhoneThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "John", "Doe", null, "123 Main St"));
    }

    @Test
    void phoneUnder10DigitsThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "John", "Doe", "123456789", "123 Main St"));
    }

    @Test
    void phoneOver10DigitsThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "John", "Doe", "12345678901", "123 Main St"));
    }

    @Test
    void phoneWithNonDigitsThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "John", "Doe", "123-456-78", "123 Main St"));
    }

    // --- address ---

    @Test
    void nullAddressThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "John", "Doe", "1234567890", null));
    }

    @Test
    void addressOver30CharsThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "John", "Doe", "1234567890", "1234567890123456789012345678901"));
    }

    @Test
    void addressExactly30CharsIsAllowed() {
        assertDoesNotThrow(() -> new Contact("1", "John", "Doe", "1234567890", "123456789012345678901234567890"));
    }

    // --- setters after construction ---

    @Test
    void setFirstNameValidatesLikeConstructor() {
        Contact c = validContact();
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName("12345678901"));
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
    }

    @Test
    void setPhoneValidatesLikeConstructor() {
        Contact c = validContact();
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("abc"));
        c.setPhone("9998887777");
        assertEquals("9998887777", c.getPhone());
    }
}
