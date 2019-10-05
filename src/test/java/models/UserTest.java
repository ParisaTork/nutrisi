package models;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    User user;

    @Before
    public void setUp() { user = new User("Natalie", "natalie@yahoo.com","pass1234");}

    @Test
    public void itHasName() { assertEquals("Natalie", user.getName()); }

    @Test
    public void itHasEmail() { assertEquals("natalie@yahoo.com", user.getEmail()); }

    @Test
    public void itHasPassword() {assertEquals("pass1234", user.getPassword()); }

    @After
    public void tearDown() { user = null; }






}
